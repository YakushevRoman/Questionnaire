package yakushevroman1991.questionnaire;
    /**
    * This`s database parameters for to create its tables
    */
    /**
    * first the table
    */
    public class DataBaseSellerChema {
        // the Seller_TABLE parameters
        public static class Seller_TABLE{
            //  the table name
            public static final String NAME = "seller_table";
            // the table columns
            public static class Columns {
                public static final String ID = "id_seller";
                public static final String NAME_PEOPLE = "name_people";
            }
        }

    /**
     * second the table
     */
    public static class INFORMATION_TABLE{
        // the INFORMATION_TABLE parameters
        public static final String NAME = "information_table";
        // the table columns
        public static class Columns {
            public static final String TIME = "time";
            public static final String QUESTIONNAIRE = "questionnaire";
            public static final String ID = "id_time";
        }
    }
}
