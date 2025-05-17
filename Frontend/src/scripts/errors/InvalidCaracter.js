export class InvalidCaracter extends Error {
    constructor(message) {
        super(message)
        this.name = "InvalidPassword"
    }
}