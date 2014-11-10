(defproject kormas "0.1.0-SNAPSHOT"
  :description "Utility functions for Korma (A Clojure Library for Tasty SQL)"
  :url "https://github.com/federkasten/kormas"
  :license {:name "Apache License, Version 2.0"
            :url "http://www.apache.org/licenses/LICENSE-2.0.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [korma "0.4.1-SNAPSHOT"]
                 [mysql/mysql-connector-java "5.1.34"]
                 [log4j "1.2.17" :exclusions [javax.mail/mail
                                              javax.jms/jms
                                              com.sun.jdmk/jmxtools
                                              com.sun.jmx/jmxri]]])
