(ns euler.problem18)

;; Find the maximum total from top to bottom of the triangle below:

(def triangle-list
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
     (4  62 98 27 23 9  70 98 73 93 38 53 60  4 23)))

(defn triangle-list-with-coords
  [triangle-list]
  (for [[row-idx row] (map-indexed vector triangle-list)]
     (for [[col-idx val] (map-indexed vector row)]
       [[(+ 1 row-idx) (+ 1 col-idx)] val])))

(def triangle-map 
     (apply hash-map
	    (apply concat
		   (apply concat
			  (triangle-list-with-coords triangle-list)))))

(defn get-adjacent-nodes
  [row col]
  )

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







