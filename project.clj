(defproject kormas "0.1.0-SNAPSHOT"
  :description "Wrapper with Utility Functions for Korma (A Clojure Library for Tasty SQL)"
  :url ""
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [korma "0.4.1-SNAPSHOT"]
                 [mysql/mysql-connector-java "5.1.33"]
                 [log4j "1.2.17" :exclusions [javax.mail/mail
                                              javax.jms/jms
                                              com.sun.jdmk/jmxtools
                                              com.sun.jmx/jmxri]]])
