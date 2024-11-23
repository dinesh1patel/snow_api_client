package com.snowapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class ResultTaskVariable {
    TaskVariable [] result;

    public TaskVariable[] getResult() {
        return result;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public class TaskVariable {

        String number;
        String sys_id;
        String variables_request_type;
        String variables_ccd_account_profile_add;
        String variables_ccd_account_new_surname;
        String variables_email;
        String variables_ccd_account_new_first_name;
        String variables_ccd_account_amend_type;
        String variables_ccd_account_profile_remove_roles;
        String variables_ccd_account_jurisdiction;
        String variables_ccd_account_new_email_address;
        String variables_var_set_user_to_mirror;
        String variables_ccd_account_date_required_by;
        String variables_surname;
        String variables_firstname;

        public String getVariables_firstname() {
            return variables_firstname;
        }

        public String getVariables_ccd_account_profile_add() {
            return variables_ccd_account_profile_add;
        }

        public String getVariables_ccd_account_new_surname() {
            return variables_ccd_account_new_surname;
        }

        public String getVariables_email() {
            return variables_email;
        }

        public String getVariables_ccd_account_new_first_name() {
            return variables_ccd_account_new_first_name;
        }

        public String getVariables_ccd_account_amend_type() {
            return variables_ccd_account_amend_type;
        }

        public String getVariables_ccd_account_profile_remove_roles() {
            return variables_ccd_account_profile_remove_roles;
        }

        public String getVariables_ccd_account_jurisdiction() {
            return variables_ccd_account_jurisdiction;
        }

        public String getVariables_ccd_account_new_email_address() {
            return variables_ccd_account_new_email_address;
        }

        public String getVariables_var_set_user_to_mirror() {
            return variables_var_set_user_to_mirror;
        }

        public String getVariables_ccd_account_date_required_by() {
            return variables_ccd_account_date_required_by;
        }

        public String getVariables_surname() {
            return variables_surname;
        }

        public String getNumber() {
            return number;
        }

        public String getSys_id() {
            return sys_id;
        }

        public String getVariables_request_type() {
            return variables_request_type;
        }

        @Override
        public String toString() {
            return "TaskVariable{" +
                    "number='" + number + '\'' +
                    ", sys_id='" + sys_id + '\'' +
                    ", variables_request_type='" + variables_request_type + '\'' +
                    ", variables_ccd_account_profile_add='" + variables_ccd_account_profile_add + '\'' +
                    ", variables_ccd_account_new_surname='" + variables_ccd_account_new_surname + '\'' +
                    ", variables_email='" + variables_email + '\'' +
                    ", variables_ccd_account_new_first_name='" + variables_ccd_account_new_first_name + '\'' +
                    ", variables_ccd_account_amend_type='" + variables_ccd_account_amend_type + '\'' +
                    ", variables_ccd_account_profile_remove_roles='" + variables_ccd_account_profile_remove_roles + '\'' +
                    ", variables_ccd_account_jurisdiction='" + variables_ccd_account_jurisdiction + '\'' +
                    ", variables_ccd_account_new_email_address='" + variables_ccd_account_new_email_address + '\'' +
                    ", variables_var_set_user_to_mirror='" + variables_var_set_user_to_mirror + '\'' +
                    ", variables_ccd_account_date_required_by='" + variables_ccd_account_date_required_by + '\'' +
                    ", variables_surname='" + variables_surname + '\'' +
                    '}';
        }
    }
}

