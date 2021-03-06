(ns structured-data)

(defn do-a-thing [x]
  (let [z (+ x x)]
    (Math/pow z z)))

(defn spiff [v]
  (+ (get v 0) (get v 2)))

(defn cutify [v]
  (conj v "<3"))

(defn spiff-destructuring [v]
  (let [[x y z] v]
    (+ x z)))

(defn point [x y]
  [x y])

(defn rectangle [bottom-left top-right]
  [bottom-left top-right])

(defn width [rectangle]
  (let [[[x1 y1] [x2 y2]] rectangle]
    (- x2 x1)))

(defn height [rectangle]
  (let [[[x1 y1] [x2 y2]] rectangle]
    (- y2 y1)))

(defn square? [rectangle]
  (= (height rectangle) (width rectangle)))

(defn area [rectangle]
  (* (height rectangle) (width rectangle)))

(defn contains-point? [rectangle point]
  (let [ [[x1 y1] [x2 y2]] rectangle [x y] point]
    (and (<= x1 x x2) (<= y1 y y2))))

(defn contains-rectangle? [outer inner]
  (let [ [p1 p2] inner]
    (and (contains-point? outer p1) (contains-point? outer p2))))

(defn title-length [book]
  (count (:title book)))

(defn author-count [book]
  (count (:authors book)))

(defn multiple-authors? [book]
  (> (author-count book) 1))

(defn add-author [book new-author]
  (let [original book new-book (assoc book :authors (conj (:authors book) new-author))]
    new-book))

(defn alive? [author]
  (not (contains? author :death-year)))


(defn element-lengths [collection]
  (map count collection))

(defn second-elements [collection]
  (let [get-second (fn [coll] (get coll 1))] (map get-second collection)))

(defn titles [books]
  (map :title books))

(defn monotonic? [a-seq]
  (or (apply <= a-seq) (apply >= a-seq)))

(defn stars [n]
  (apply str (repeat n "*")))

(defn toggle [a-set elem]
  (if (contains? a-set elem)
    (disj a-set elem)
    (conj a-set elem)))

(defn contains-duplicates? [a-seq]
  (not (= (seq (set a-seq)) (seq a-seq))))

(defn old-book->new-book [book]
  (let [new-book (assoc book :authors (set (:authors book)))]
    new-book))

(defn has-author? [book author]
  (contains? (:authors book) author))

(defn authors [books]
 (apply clojure.set/union (map set (map :authors books))))

(defn all-author-names [books]
(set (map :name (authors books))))

(defn author->string [author]
  (let [name (:name author) years (if (contains? author :birth-year)
                                      (str " (" (:birth-year author) " - " (:death-year author) ")" ))]
    (str name years)))

(defn authors->string [authors]
  (apply str (interpose ", " (map author->string authors))))

(defn book->string [book]
  (let [title (:title book) authors (authors->string (:authors book))]
    (str title ", written by " authors)))

(defn books->string [books]
  (let [book-string (apply str (interpose ". " (map book->string books) )) book-count (count books)]
  (cond
    (= book-count 0) "No books."
    (= book-count 1) (str "1 book. " book-string ".")
    :else (str book-count " books. "  book-string "."))))

(defn books-by-author [author books]
  (filter (fn [book] (has-author? book author)) books))

(defn author-by-name [name authors]
  (let [author (filter (fn [author] (= (:name author) name)) authors)]
    (if (> (count author) 0)
      (first author)
      nil)))

(defn living-authors [authors]
  (filter alive? authors))

(defn has-a-living-author? [book]
  (not (empty? (living-authors (:authors book)))))

(defn books-by-living-authors [books]
  (filter has-a-living-author? books))

; %________%
