(ns aoc.main
  (:require [clojure.pprint :as pp]))

(defn run [opts]
  (pp/pprint opts)
  (println "Hello, World!"))

(do 
  (defn add
    "Hello, World!
    This is a function."
    [a b]
    (+ a b))

  (add 3 5)
)

(run "")


