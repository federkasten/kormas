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
