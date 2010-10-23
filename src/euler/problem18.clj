(ns euler.problem18)

;; Find the maximum total from top to bottom of the triangle below:

(def triangle-rows
   '((75)
     (95 64)
     (17 47 82)
     (18 35 87 10)
     (20 4  82 47 65)
     (19 1  23 75  3 34)
     (88 2  77 73  7 63 67)
     (99 65  4 28  6 16 70 92)
     (41 41 26 56 83 40 80 70 33)
     (41 48 72 33 47 32 37 16 94 29)
     (53 71 44 65 25 43 91 52 97 51 14)
     (70 11 33 28 77 73 17 78 39 68 17 57)
     (91 71 52 38 17 14 91 43 58 50 27 29 48)
     (63 66 4  68 89 53 67 30 73 16 69 87 40 31)
     (4  62 98 27 23 9  70 98 73 93 38 53 60 04 23)))

;; start small...

;; 1 row

;; it's just the value in the first row

;; 2 rows

;; it's the value in the first row and the max of the second row (both options are available still)

;; 3 rows -- first "real" case where certain options are impossible

;; it's the value in the first row and the max combination of adjacent values in the second and third rows

;; how to rule out possibilities in order not to brute force the max combo of adjacents?

;; maybe work backwards? work in smaller triangle partitions?

;; how to encode the triangle? to uniquely identify each node and determine adjacency
;; you need to specify the row and the column.

;; it will also be useful to store the weight and pointers to any adjacent nodes 

(defn node
  [row col wt nodes]
  {:row row :col col :wt wt :nodes nodes})

;;;; the "start" node must have a :row that is 1 less then the "end" node.
(defn row-adjacent?
  [start-row end-row]
  (= (- end-row start-row) 1))

;;;; the "end" node must be equal to the "start" nodes :col - 1, :col or :col + 1
(defn col-adjacent?
  [start-col end-col]
  (<= (Math/abs (- end-col start-col)) 1))

;; for two nodes to be adjacent:
(defn adjacent?
  [start end]
  (and
    (row-adjacent? (start :row) (end :row)) 
    (col-adjacent? (start :col) (end :col))))

;; need to go from this

(def mini-triangle
   '((75)
     (95 64)
     (17 47 82)
     (18 35 87 10)))

;; to this

(def mini-triangle-res
   '((1 1 75)
     (2 1 95) (2 2 64)
     (3 1 17) (3 2 47) (3 3 82)))

(defn index-triangle-rows
  [triangle]
  (zipmap (iterate inc 1) triangle))

(index-triangle-rows mini-triangle)

(defn index-triangle-cols
  [triangle]
  (map #(zipmap (iterate inc 1) %) triangle))

(index-triangle-rows (index-triangle-cols mini-triangle))

(defn index-items
  [coll]
  (interleave (iterate inc 1) coll))
  
(index-items '(1 (1 2 32 3) 4 5 6))00
  
(partition 2 (index-items (index-triangle-cols mini-triangle)))

(map #(interleave (conj () (first %)) (rest %)) '(1 {1 75}))




