(ns kormas.core)

(def ^:private db-test-connection
  {:test-connection-query "SELECT 1"
   :test-connection-on-checkin true
   :idle-connection-test-period 300})

(defmacro definit
  [name params & body]
  `(let [current-ns# *ns*]
     (defn ~name ~params
       (binding [*ns* current-ns#]
         ~@body)
       nil)))

;;; MySQL

(def ^:private mysql-db-unicode-option {"useUnicode" true
                                        "characterEncoding" "utf8"})

(defn mysql-db-config
  [{:keys [user password host port db]
    :or {password ""
         host "localhost"
         port 3306}}]
  (merge {:user user
          :password password
          :host host
          :port port
          :db db}
         db-test-connection
         mysql-db-unicode-option))
