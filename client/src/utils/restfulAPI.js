import Ajv from 'ajv';
import * as configSchema from '../../schemas/ConfigResponse';
import * as loginSchema from '../../schemas/LoginResponse';
import * as logoutSchema from '../../schemas/LogoutResponse';
import * as newUserSchema from '../../schemas/NewUserResponse';

import { LOG } from './constants';

const SCHEMAS = {
    config: configSchema,
    Login: loginSchema,
    Logout: logoutSchema,
    NewUser: newUserSchema,
}

export async function sendAPIRequest(requestBody, serverUrl) {

    const response = await sendRequest(requestBody, serverUrl);

    if (!Object.keys(SCHEMAS).includes(requestBody.requestType)) {
        throw new Error(`sendAPIRequest() does not have support for type: ${requestBody.requestType}. Please add the schema to 'SCHEMAS'.`);
    }
    if (isJsonResponseValid(response, SCHEMAS[requestBody.requestType])) {
        return response;
    }
    LOG.error(`Server ${requestBody.requestType} response json is invalid. Check the Server.`);
    return null;
}

async function sendRequest(requestBody, serverUrl) {
    const fetchOptions = {
        method: "POST",
        body: JSON.stringify(requestBody)
    };

    try {

        const response = await fetch(`${serverUrl}/api/${requestBody.requestType}`, fetchOptions);

        if (response.ok) {
           // console.log("response is ok");
            return response.json();
        } else {
           // console.log("logging error");
           // console.log(response.status);
            //console.log(response.statusText);
            LOG.error(`Request to server ${serverUrl} failed: ${response.status}: ${response.statusText}`);
        }

    } catch (err) {
        //console.log("catch block");
        LOG.error(`Request to server failed : ${err}`);
    }

    return null;
}

export function getOriginalServerUrl() {
    const serverProtocol = location.protocol;
    const serverHost = location.hostname;
    const serverPort = location.port;
    const alternatePort = process.env.SERVER_PORT;
    return `${serverProtocol}\/\/${serverHost}:${(!alternatePort ? serverPort : alternatePort)}`;
}

export function isJsonResponseValid(object, schema) {

    if (object && schema) {
        //console.log("Json response is valid");
        const anotherJsonValidator = new Ajv();
        const validate = anotherJsonValidator.compile(schema);
        return validate(object);
    }
    //console.log("Json response is NOT valid");
    LOG.error(`bad arguments - isJsonResponseValid(object: ${object}, schema: ${schema})`);
    return false;
}

