package com.example.mockmvc.form;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberForm {

    public static class Request {

        @Getter
        @NoArgsConstructor
        public static class Join {

            private String memberId;
            private String password;

            @Builder
            public Join(String memberId, String password) {
                this.memberId = memberId;
                this.password = password;
            }
        }

        @Getter
        @NoArgsConstructor
        public static class Modify {

            private String memberId;
            private String password;

            @Builder
            public Modify(String memberId, String password) {
                this.memberId = memberId;
                this.password = password;
            }
        }
    }
}
