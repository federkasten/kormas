(ns kormas
  (:require [clojure.java.io :as io]
            [clojure.string :as cstr]
            (korma [core :refer :all]
                   [db :refer :all])))

(def ^:private db-test-connection
  {:test-connection-query "SELECT 1"
   :test-connection-on-checkin true
   :idle-connection-test-period 300})

(def ^:private db-unicode-option {"useUnicode" true
                                  "characterEncoding" "utf8"})

(defmacro definit
  [name params & body]
  `(let [current-ns# *ns*]
     (defn ~name ~params
       (binding [*ns* current-ns#]
         ~@body)
       nil)))

(defn db-config
  [{:keys [user password host port db extra]
    :or {password ""
         host "localhost"
         port 3306}}]
  (merge {:user user
          :password password
          :host host
          :port port
          :db db}
          db-test-connection
          {:extra (merge db-unicode-option
                         extra)}))

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

;;; Utility

(defmacro swap
  [m k f]
  `(if-not (nil? (get ~m ~k))
     (assoc ~m ~k (~f (get ~m ~k)))
     ~m))

(defn transform-key
  [v k new-k]
  (if (contains? v k)
    (-> v
        (assoc new-k (k v))
        (dissoc k))
    v))
