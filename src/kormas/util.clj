(ns kormas.util)

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

(defn generated-key
  [result-of-insert]
  (or (:GENERATED_KEY result-of-insert)
      (:generated_key result-of-insert)))
