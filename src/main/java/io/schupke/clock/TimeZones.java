package io.schupke.clock;

public enum TimeZones {
    PST {
        public String toString() {
            // -8; PDT -7
            return "America/Los_Angeles";
        }
    },

    EST {
        public String toString() {
            // -5; EDT -4
            return "America/New_York";
        }
    },

    UTC {
        public String toString() {
            // 0
            return "GMT";
        }
    },

    CET {
        public String toString() {
            // +1; CEST +2
            return "Europe/Paris";
        }
    },

    KST {
        public String toString() {
            // +9
            return "Asia/Seoul";
        }
    }
} 
