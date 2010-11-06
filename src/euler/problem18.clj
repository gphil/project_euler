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
       [[(+ 1 row-idx) (+ 1 col-idx)] {:visited? false :dist nil :wt val}])))

(def triangle-map 
     (apply hash-map
	    (apply concat
		   (apply concat
			  (triangle-list-with-coords triangle-list)))))

;; ref our initial triangle map so we can mutate it now
(def triangle (ref triangle-map))

;; utility method for getting a seq of adjacent coordinates from a
;; pair of coordinates
(defn get-adjacent-coords [[x y]]
  (filter #(not (nil? %))
   (for [new-y (range (- y 1) (+ y 2))]
    (if (> new-y 0)
      [(+ x 1) new-y]))))

;; do the djikstra:

;; Assign to every node a distance value. Set it to zero for our initial
;; node and to infinity for all other nodes.  Mark all nodes as
;; unvisited. Set initial node as current.  For current node, consider
;; all its unvisited neighbors and calculate their tentative
;; distance (from the initial node). For example, if current node (A) has
;; distance of 6, and an edge connecting it with another node (B) is 2,
;; the distance to B through A will be 6+2=8. If this distance is less
;; than the previously recorded distance (infinity in the beginning, zero
;; for the initial node), overwrite the distance.  When we are done
;; considering all neighbors of the current node, mark it as visited. A
;; visited node will not be checked ever again; its distance recorded now
;; is final and minimal.  If all nodes have been visited,
;; finish. Otherwise, set the unvisited node with the smallest
;; distance (from the initial node) as the next "current node" and
;; continue from step 3.

;; set initial node to zero, in a transaction:
(dosync (alter triangle update-in [1 1] assoc :dist 0))

