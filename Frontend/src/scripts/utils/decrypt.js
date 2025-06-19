export function decodeJwtPayload(payload) {
    const json = atob(payload);
    return JSON.parse(json).sub;
}