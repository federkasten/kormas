(ns kormas.sql
  (:require [clojure.java.io :as io]
            [clojure.string :as string]
            (korma [core :refer :all]
                   [db :refer :all])))

(defn- read-sql
  [f]
  (let [raw-sql (slurp f)]
    (filter #((complement nil?) (re-find #"\w+" %))
            (string/split raw-sql #";"))))

(defn exec-sql-into-db
  [f db]
  (with-db db
    (doseq [sql (read-sql (io/resource f))]
      (exec-raw (get-connection db) [sql])))
  nil)
