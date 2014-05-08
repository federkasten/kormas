(ns kormas
  (:require [clojure.java.io :as io]
            [clojure.string :as cstr]
            (korma [core :refer :all]
                   [db :refer :all])))

(def ^:private db-test-connection
  {:test-connection-query "SELECT 1"
   :test-connection-on-checkin true
   :idle-connection-test-period 300})

(def ^:private db-option
  {:extra {"useUnicode" true
           "characterEncoding" "utf8"}})

(defmacro definit
  [name params & body]
  `(let [current-ns# *ns*]
     (defn ~name ~params
       (binding [*ns* current-ns#]
         ~@body)
       nil)))

(defn db-config
  [{:keys [user password host db]}]
  (merge {:user user
          :password password
          :host host
          :db db}
          db-test-connection
          db-option))

(defn- read-sql
  [f]
  (let [raw-sql (slurp f)]
    (filter #(not (nil? (re-find #"\w+" %))) (cstr/split raw-sql #";"))))

(defn exec-sql-into-db
  [f db]
  (with-db db
    (doseq [sql (read-sql (io/resource f))]
      (exec-raw (get-connection db) [sql])))
  nil)
