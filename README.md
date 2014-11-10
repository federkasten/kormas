# kormas

Utility functions for Korma (A Clojure Library for Tasty SQL)

## Usage

```clj
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

Copyright 2014 [Takashi AOKI][tak.sh]

Licensed under the [Apache License, Version 2.0][apache-license-2.0].

[tak.sh]: http://tak.sh
[apache-license-2.0]: http://www.apache.org/licenses/LICENSE-2.0.html
