package com.example.Passenger.models;

// класс, используемый для передачи данных
public class SortParam {
        public String name;

        public void setName(String name) {
                this.name = name;
        }

        public String getName() {
                return name;
        }
        public int numBegin=0;
        public int sizeBegin;
        public int survived;
        public int age;
        public int sex;
        public int siblings;
        public int parents;
        public int countPassenger;
        public int sortBy;

        public int getNumBegin() {
                return numBegin;
        }
        public void setNumBegin(int numBegin) {
                this.numBegin = numBegin;
        }
        public int getSizeBegin() {
                return sizeBegin;
        }
        public void setSizeBegin(int sizeBegin) {
                this.sizeBegin = sizeBegin;
        }
        public int getSortBy() {
                return sortBy;
        }
        public void setSortBy(int sortBy) {
                this.sortBy = sortBy;
        }
        public int getSurvived() {
                return survived;
        }
        public void setSurvived(int survived) {
                this.survived = survived;
        }
        public int getAge() {
                return age;
        }
        public void setAge(int age) {
                this.age = age;
        }
        public int getSex() {
                return sex;
        }
        public void setSex(int sex) {
                this.sex = sex;
        }
        public int getSiblings() {
                return siblings;
        }
        public void setSiblings(int siblings) {
                this.siblings = siblings;
        }
        public int getParents() {
                return parents;
        }
        public void setParents(int parents) {
                this.parents = parents;
        }
        public int getCountPassenger() {
                return countPassenger;
        }
        public void setCountPassenger(int countPassenger) {
                this.countPassenger = countPassenger;
        }
        @Override
        public String toString() {
                return "SortParam{" +
                        "name='" + name + '\'' +
                        ", survived=" + survived +
                        ", age=" + age +
                        ", sex=" + sex +
                        ", siblings=" + siblings +
                        ", parents=" + parents +
                        ", countPassenger=" + countPassenger +
                        '}';
        }
}
