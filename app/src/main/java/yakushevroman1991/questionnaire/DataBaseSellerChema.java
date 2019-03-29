package yakushevroman1991.questionnaire;

class DataBaseSellerChema {

     static class Seller_TABLE{
         static final String NAME = "seller_table";
         static class Columns {
             static final String ID = "id_seller";
             static final String NAME_PEOPLE = "name_people";
        }
    }

     static class INFORMATION_TABLE{
         static final String NAME = "information_table";
         static class Columns {
             static final String TIME = "time";
             static final String QUESTIONNAIRE = "questionnaire";
             static final String ID = "id_time";
        }
    }
}
