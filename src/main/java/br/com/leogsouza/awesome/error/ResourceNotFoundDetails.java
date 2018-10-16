package br.com.leogsouza.awesome.error;

public class ResourceNotFoundDetails extends ErrorDetail {

    public static final class Builder {
        private String title;
        private int status;
        private String details;
        private Long timestamp;
        private String developerMessage;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder status(int status) {
            this.status = status;
            return this;
        }

        public Builder details(String details) {
            this.details = details;
            return this;
        }

        public Builder timestamp(Long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder developerMessage(String developerMessage) {
            this.developerMessage = developerMessage;
            return this;
        }

        public ResourceNotFoundDetails build() {
            ResourceNotFoundDetails resourceNotFoundDetails = new ResourceNotFoundDetails();
            resourceNotFoundDetails.setTitle(title); 
            resourceNotFoundDetails.setTimestamp(timestamp); 
            resourceNotFoundDetails.setDeveloperMessage(developerMessage); 
            resourceNotFoundDetails.setDetails(details); 
            resourceNotFoundDetails.setStatus(status); 
            return resourceNotFoundDetails;
        }
    }
}
