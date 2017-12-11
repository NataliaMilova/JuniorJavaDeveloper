package patterns.builder;

/**
 * Created by evami on 21.11.17.
 */
public class Pizza {
    private final String dough;
    private final String sauce;
    private final String meat;
    private final String tomato;
    private final String cheese;
    private final String mushrooms;
    private final String olives;
    private final String pickle;
    private final String jalapeno;
    private final String pepper;

    public class Builder{
        private final String dough;
        private final String sauce;
        private final String cheese;

        private  String meat = null;
        private  String tomato = null;
        private  String mushrooms = null;
        private  String olives = null;
        private  String pickle = null;
        private  String jalapeno = null;
        private  String pepper = null;

        public Builder(String dough, String sauce, String cheese) {
            this.dough = dough;
            this.sauce = sauce;
            this.cheese = cheese;
        }

        public Builder tomato(String val){
            this.tomato = val;
            return this;
        }

        public Builder meat(String val){
            this.meat = val;
            return this;
        }

        public Builder mushrooms(String val){
            this.mushrooms = val;
            return this;
        }

        public Builder olives(String val){
            this.olives = val;
            return this;
        }

        public Builder pickle(String val){
            this.pickle = val;
            return this;
        }

        public Builder jalapeno(String val){
            this.jalapeno = val;
            return this;
        }

        public Builder pepper(String val){
            this.pepper = val;
            return this;
        }

        public Pizza build(){
            return new Pizza(this);
        }
    }

    private Pizza(Builder builder) {
        this.dough = builder.dough;
        this.sauce = builder.sauce;
        this.meat = builder.meat;
        this.tomato = builder.tomato;
        this.cheese = builder.cheese;
        this.mushrooms = builder.mushrooms;
        this.olives = builder.olives;
        this.pickle = builder.pickle;
        this.jalapeno = builder.jalapeno;
        this.pepper = builder.pepper;
    }
}
