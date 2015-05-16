# kormas

Utility functions for [Korma (A Clojure Library for Tasty SQL)][korma]

This library is WIP.

```
[kormas "0.1.0-SNAPSHOT"]
```

## Usage

```clojure
(require '[kormas.core :refer [definit]])
(require '[kormas.util :refer [swap transform-key]])
```

```clojure
(definit db-init
  [user password]

  ;; db
  (defdb main-db
    (mysql (db-config {:user user
                       :password password
                       :host "localhost"
                       :db "mydb"})))

  ;; entities
  (defentity sam
    (database main-db)
    (prepare (fn [v]
               (-> v
                   (swap :status keyword)
                   (swap :enable #(= 0 %)))))
    (transform (fn [v]
                 (-> v
                     (swap :status str)
                     (swap :enable #(if % 0 1))))))

  )
```

## License

Copyright [Takashi AOKI][tak.sh]

Licensed under the [Apache License, Version 2.0][apache-license-2.0].

[korma]: http://sqlkorma.com/
[tak.sh]: http://tak.sh
[apache-license-2.0]: http://www.apache.org/licenses/LICENSE-2.0.html
