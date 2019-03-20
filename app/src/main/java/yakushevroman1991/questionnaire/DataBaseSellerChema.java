package yakushevroman1991.questionnaire;

public class DataBaseSellerChema {

    public static class Seller_TABLE{
        public static final String NAME = "seller_table";
        public static class Columns {
            public static final String ID = "id_seller";
            public static final String NAME_PEOPLE = "name_people";
        }
    }

    public static class INFORMATION_TABLE{
        public static final String NAME = "information_table";
        public static class Columns {
            public static final String TIME = "time";
            public static final String QUESTIONNAIRE = "questionnaire";
            public static final String ID = "id_time";
        }
    }
}
