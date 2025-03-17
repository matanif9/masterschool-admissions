package com.masterschool;

import java.util.Arrays;
import java.util.List;

public class AdmissionsFlow {
    public static List<Step> createFlow() {
        Task personalDetails = new SimpleTask("personal_details");
        Task iqTest = new IqTestTask();
        Task secondChance = new SecondChanceTask();
        Task scheduleInterview = new SimpleTask("schedule_interview");
        Task performInterview = new InterviewTask();
        Task uploadId = new SimpleTask("upload_id");
        Task signContract = new SimpleTask("sign_contract");
        Task payment = new SimpleTask("payment");
        Task joinSlack = new SimpleTask("join_slack");

        return Arrays.asList(
            new Step("Personal Details", List.of(personalDetails)),
            new Step("IQ Test", List.of(iqTest, secondChance)),
            new Step("Interview", List.of(scheduleInterview, performInterview)),
            new Step("Sign Contract", List.of(uploadId, signContract)),
            new Step("Payment", List.of(payment)),
            new Step("Join Slack", List.of(joinSlack))
        );
    }
}
