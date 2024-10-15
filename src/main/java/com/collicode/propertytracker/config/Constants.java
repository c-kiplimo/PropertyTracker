package com.collicode.propertytracker.config;

public class Constants {

    public static final int COST_PER_SMS=2;
    public static final long   ACCESS_TOKEN_VALIDITY_SECONDS = 86400;
    public static final long   ACCESS_TOKEN_VALIDITY_REMEMBER_ME_SECONDS = 2592000L;
    public static final String SIGNING_KEY = "20201LIPACHATQNATUJENGEQAZ2wsx4rfvBHUZ2wsx4rfvBHU*";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

    public static final String API_ENDPOINT = "http://51.15.243.105:4200";
    public static final int PLAN_VALIDITY_PERIOD = 30;
    public static final int SMS_BUNDLE_VALIDITY_PERIOD = 30;
    public static final float COMMISSION_PERCENTAGE = 0.1f;

    public static final String NOTIFICATION_URL = "http://51.15.233.87:15432/message/queue";

    public static final String TIARA_CONNECT_ENDPOINT = " http://51.15.143.13:8787/api/campaign/trigger";
    public static final String DEFAULT_EMAIL_SOURCE_ADDRESS = "support@bakeconnect.app";
    public static final String DEFAULT_SUPPORT_EMAIL_SOURCE_ADDRESS = "support@bakeconnect.app";
    public static final String DEFAULT_SMS_SOURCE_ADDRESS = "BAKECONNECT";

    public static final String UPLOADS_FOLDER ="/apps/uploads";

    public static final String TOP_UP_URL = "https://payment-gw.meliora.co.ke/api/charge";
//    public static final String TOP_UP_URL = "http://51.15.194.173:8585/api/charge";
    public static final String TOP_UP_PRODUCT_ID = "5b93c1bc-8f42-420f-89f3-e39d0d5b7724";

    public static  final String[] productCategories= {"Bread","Cakes","Cupcake/Muffins","Pastries"};
    public static  final String[] expenseCategories= {"Electricity & water","Transport","Salaries","Airtime","Food","Rent"};
    public static  final String[] orderEventTypes= {"Anniversary","Baby shower(Boy)","Baby shower(Girl)","Baptism","Birthday","Birthday 6 Months","Birthday(Adult-Guy)","Birthday(Adult-Lady)","Birthday(Boy)","Birthday(Girl)","Bridal shower","Celebrating business milestones/achievements","Congratulations","End of year party","Farewell","Father's day","Gender reveal","Gift","Graduation","Homecoming","House warming/blessings","I'm sorry","Launching/Commissioning business/products","Mother's Day","Promotion","Proposal And Engagement","Religious Celebration(christmas, Diwali, Eid-Mubarak)","Thank You","Thanks Giving","Valentine's Day","Wedding"};

    //Baker notifications
    public static final String DEFAULT_NEW_ORDER_SMS_TEMPLATE = "$business_name: Dear $customer_name, thank you for your order $order_number, due on $due_date, of KES $order_amount. $deposit_and_payment_details Order details here: \n\n$link \nAccess code: $access_code";
    public static final String DEFAULT_NEW_ORDER_REQUEST_SMS_TEMPLATE = "Hello $business_name, you have a new order request from $customer_name. See details here: $link";

    public static final String DEFAULT_PARTIAL_ORDER_PAYMENT_TEMPLATE =  "$business_name: Dear $customer_name, We have received KES $amount_paid paid on $paid_on for order $order_number. Your balance is KES $payment_balance. Thank you!";

    public static final String FULL_ORDER_PAYMENT_TEMPLATE =  "$business_name: Dear $customer_name, We have received KES $amount_paid paid on $paid_on for order $order_number. Your balance is KES $payment_balance. Thank you!";

    public static final String CANCEL_ORDER_TEMPLATE = "$business_name: Dear $customer_name, Your order $order_number that was due on $due_date has been cancelled successfully. Thank you!";

    public static final String DELIVER_ORDER_TEMPLATE = "$business_name: Dear $customer_name, Your order $order_number has been delivered to the advised destination. Your balance is KES $payment_balance. $payment_details Thank you for your business. We look forward to receiving your feedback!";

    public static final String ORDER_DELIVERY_ON_THE_WAY_TEMPLATE = "$business_name: Dear $customer_name, Your order $order_number is on its way, dispatched at $dispatch_time on $dispatch_date. Thank you!";

    public static final String DEFAULT_PAYMENT_REMINDER_TEMPLATE =  "$business_name: Dear $customer_name, just a quick reminder about your KES $payment_balance balance for order $order_number, fulfilled on $due_date. $payment_details Thanks for choosing $business_name, and let us know if you have any questions. Best regards!";

    public static final String ORDER_REQUEST_REJECTED_TEMPLATE = "$business_name : Dear $customer_name, Thank you for your order request. Unfortunately we are unable to accept it at this time. Reason: $reason. You can reach us on $baker_msisdn for inquiries.";

    // System notifications
    public static final String SPECIAL_DATE_REMINDER_TEMPLATE = "Hi $business_name, Some of your customers ($customer) have a special day coming up on $special_date. Great chance to reach out and offer your services before anyone else does!";
    public static final String UPCOMING_ORDER_REMINDER_7_DAYS_TEMPLATE = "Hi $business_name! You have $no_of_orders order(s) coming up in 7 days - $due_date";
    public static final String UPCOMING_ORDER_REMINDER_2_DAYS_TEMPLATE = "Just a friendly reminder: you have $no_of_orders order(s) coming up in 2 days - $due_date. Please check that you have all the necessary ingredients and supplies you need. Thank you!";
    public static final String UPCOMING_ORDER_REMINDER_0_DAYS_TEMPLATE = "Hi $business_name! You have $no_of_orders order(s) to service today, $due_date! See details here: https://www.bakeconnect.app/orders.";

    // Free notifications
    public static final String SUBSCRIPTION_EXPIRY_REMINDER_TEMPLATE = "Dear $business_name, Your subscription to Bake Connect services will expire on $expiry_date. Please renew your subscription to continue enjoying our services";
    public static final String NEW_BAKER_WELCOME_MESSAGE_TEMPLATE = "Hello $baker_name,\nWelcome to Bake Connect! We're thrilled to have you on board $preloaded_amount_details. We're continuously working to ensure you have a seamless experience, and your feedback is valuable to us. You can give us your feedback within the system or via this link: https://bakeconnect.app/home#contact. Enjoy!";
    public static final String TOP_UP_ACK_MESSAGE_TEMPLATE = "Dear $baker_name, your top-up $mpesa_ref of KES $top_up_amount by MPESA account $mpesa_msisdn on $transaction_date at $transaction_time was successful. New Bake Connect wallet balance is: KES $new_account_balance. Thank you!";
    public static final String SUBSCRIPTION_ACK_MESSAGE_TEMPLATE = "Dear $business_name, Your have successfully subscribed to the $plan_name plan. Expiry date: $expiry_date at $expiry_time. Thank you!";
    public static final String INSUFFICIENT_FUNDS_ALERT ="Hi $business_name. We're unable to send you some important notifications due to insufficient funds in your wallet. Use this link to top up to continue receiving notifications: https://bit.ly/3nLXS42";

}
