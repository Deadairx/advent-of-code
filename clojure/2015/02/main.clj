
(def input (slurp "./2015/02/input.txt"))

; find the surface area of the box, which is 2*l*w + 2*w*h + 2*h*l. 
; The elves also need a little extra paper for each present: the area of the smallest side.
; 2x3x4  requires 2*6 + 2*12 + 2*8  = 52 square feet of wrapping paper plus 6 square feet of slack, for a total of 58 square feet.
; 1x1x10 requires 2*1 + 2*10 + 2*10 = 42 square feet of wrapping paper plus 1 square foot of slack, for a total of 43 square feet.

(do
  (if (= (calc-wrapping "2x3x4") 58) (println "Ex 1 Success!") (println "Ex 1 FAIL!"))
  (if (= (calc-wrapping "1x1x10") 43) (println "Ex 2 Success!") (println "Ex 2 FAIL!"))
    )

(process-string input)

(defn process-string [large-input]
  (let [total (atom 0)]
    (doseq [line (clojure.string/split-lines large-input)]
      (reset! total (+ @total (calc-wrapping line)))
      )
    @total))

(defn calc-wrapping [deminsions]
  (let [[h w l] (map #(Integer/parseInt %) (clojure.string/split deminsions #"x"))
        [smallest second-smallest] (take 2 (sort [h w l]))
        ]
    (def area (+ (* 2 l w) (* 2 w h) (* 2 h l) (* smallest second-smallest)))
    (def ribbon (+ (* w h l) (+ smallest second-smallest smallest second-smallest)))
    )
  ribbon
  )
