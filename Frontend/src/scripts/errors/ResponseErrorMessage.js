export class ResponseErrorMessage extends Error {
    constructor(message) {
        super(message)
        this.name = "ResponseErrorMessage"
    }
}