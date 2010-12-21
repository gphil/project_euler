(ns euler.problem40)

(defn build-string [n]
  (apply str (range 1 (+ 1 n))))

(defn string-nth [n]
  (Integer/parseInt (str (nth (build-string 1000000) (- n 1)))))

(defn problem40 []
  (*
   (string-nth 1)
   (string-nth 10)
   (string-nth 100)
   (string-nth 1000)
   (string-nth 10000)
   (string-nth 100000)
   (string-nth 1000000)))
