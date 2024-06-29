
(def input (slurp "./2015/03/input.txt"))

(defn house-test [directions expected]
  (let [actual (house-count directions)]

    (if (= actual expected)
      (println "✅ pass")
      (println (str "❌ fail: actual " actual " expected " expected)
               )
      )
    )
  )
(defn dual-santa-house-test [directions expected]
  (let [actual (dual-santa-house-count directions)]

    (if (= actual expected)
      (println "✅ pass")
      (println (str "❌ fail: actual " actual " expected " expected)
               )
      )
    )
  )

(do
  (house-test ">" 2)
  (house-test "^>v<" 4)
  (house-test "^v^v^v^v^v" 2)
  )
(do
  (dual-santa-house-test "^v" 3)
  (dual-santa-house-test "^>v<" 3)
  (dual-santa-house-test "^v^v^v^v^v" 11)
  )
(house-count input)
(dual-santa-house-count input)

; Count how many houses visited
(defn house-count [directions]
  (let [x (atom 0)
        y (atom 0)
        count (atom 0)
        ]
    (def house-grid (atom {}))
    (defn visit [x y]
      #_(println (str "visit " x " " y))
      (swap! house-grid assoc [x y] true)
      )

    (defn visited? [x y]
      (get @house-grid [x y])
      )
    (do 
      (visit @x @y)
      (swap! count inc))

    (doseq [c (seq directions)]
      (cond 
        (= c \>) (swap! x inc)
        (= c \<) (swap! x dec)
        (= c \^) (swap! y inc)
        (= c \v) (swap! y dec)
        )

      (if (not (visited? x y)) (do 
                                 (visit x y)
                                 (swap! count inc)))

      )
    @count
    )
  )

(defn dual-santa-house-count [directions]
  (let [santa {:x (atom 0) :y (atom 0)}
        robo {:x (atom 0) :y (atom 0)}
        santa? (atom true)
        count (atom 0)
        house-grid (atom {})
        ]
    (defn visit [{:keys [x y]}]
      #_(println (str "visit " @x " " @y))
      (swap! house-grid assoc [@x @y] true)
      )

    (defn visited? [{:keys [x y]}]
      #_(println (str "visited? " @x " " @y))
      (get @house-grid [@x @y])
      )
    (defn process-movement [{:keys [x y]} c]
      #_(println (str "process-movement " @x " " @y " " c))
      (cond 
        (= c \>) (swap! x inc)
        (= c \<) (swap! x dec)
        (= c \^) (swap! y inc)
        (= c \v) (swap! y dec)
        )

      #_(println (str "process-movement check"))
      (if (not (visited? {:x x :y y})) (do 
                                 (visit {:x x :y y})
                                 (swap! count inc)))

      )

    (do 
      (visit santa)
      (swap! count inc))

    (doseq [c directions]
      (if @santa?
        (process-movement santa c)
        (process-movement robo c))
      (swap! santa? not)
      )
    @count
    )
  )
