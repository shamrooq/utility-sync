/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.beans;

/**
 *
 * @author appadmin
 */
public class SurveyAction {
    
    private int surveyId;
    private String surveyActionTitle;

    public SurveyAction() {
    }

    public SurveyAction(int surveyId, String surveyActionTitle) {
        this.surveyId = surveyId;
        this.surveyActionTitle = surveyActionTitle;
    }

    public int getSurveyId() {
        return surveyId;
    }

    public String getSurveyActionTitle() {
        return surveyActionTitle;
    }

    public void setSurveyId(int surveyId) {
        this.surveyId = surveyId;
    }

    public void setSurveyActionTitle(String surveyActionTitle) {
        this.surveyActionTitle = surveyActionTitle;
    }
    
    
}
