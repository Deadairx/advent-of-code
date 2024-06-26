
(println (System/getProperty "user.dir"))

(def input (slurp "./2015/01/input.txt"))

(def open \()
(def close \))

(declare value)

(defn process-char [value c]
  (cond
    (= c open) (swap! value inc)
    (= c close) (swap! value dec)
    :else nil
    ))

(defn process-string [s]
  (let [value (atom 0)
        step (atom 0)
        condition-met? (atom false)]
    (doseq [c (seq s)]
      (do
        (if (and (not @condition-met?) (< @value 0)) 
          (do
            (println (str "condition met at " @step))
            (reset! condition-met? true)
            (def basement @step))
          )
        (swap! step inc)
        (process-char value c))
      )
    @value)
  basement
  )

(process-string input)
