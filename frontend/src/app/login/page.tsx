'use client';
import styles from './page.module.css'
import {Button, Link, TextField} from "@mui/material";
import {Box} from "@mui/system";
import {useRef, useState} from "react";
import clientValidateForm from "@/app/security/userValidation/clientFormValidation";
import loginController from "@/app/services/loginController";
import {
    ADOPTER_PROFILE_ROUTE, MANAGER_PROFILE_ROUTE,
    SIGN_IN_BACKEND_ENDPOINT,
    SIGN_UP_ROUTE,
    STAFF_PROFILE_ROUTE
} from "@/app/constants/apiConstants";
import toJSON from "@/app/utils/readableStreamResponseBodytoJSON";
import {useRouter} from "next/navigation";
import loginServerFormValidationMapper from "@/app/security/userValidation/loginServerFormValidationMapper";

function Page() {
    const usernameRef = useRef<HTMLInputElement>(null);
    const passwordRef = useRef<HTMLInputElement>(null);
    const [isUserValid, setIsUserValid] = useState({
        username: true,
        password: true
    });
    const [errors, setErrors] = useState({
        username: "",
        password: ""
    });
    const router = useRouter();

    const handleSubmit = () => {
        let user: User = {
            username: usernameRef.current!.value,
            password: passwordRef.current!.value
        }

        // validate user credentials on client side
        let {isUserValid, errors} = clientValidateForm(user)
        setIsUserValid(isUserValid)
        setErrors(errors);

        // if user credentials are valid, try sending to server
        isUserValid.username && isUserValid.password && sendInfoToServer(user)
    }

    async function sendInfoToServer(user: UserDTO) {
        // prepare user data to send to server
        let userDTO: UserDTO = {
            username: user.username,
            password: user.password
        }
        //not sure of await here but will try
        await fetchResponse(userDTO);
    }

    const fetchResponse = async (userDTO: UserDTO) => {

        // if spring security filter chain allowed sending post request with post body instead of get parameters then we can make
        // the two controllers as one with post request
        let response = await loginController.sendPostRequest(userDTO, SIGN_IN_BACKEND_ENDPOINT);
        // toJSON util to convert ReadableStream to JSON
        let jsonResponse = await toJSON(response.body!);
        let responseStat = response.status;

        //if response status is 200, redirect to home page
        // if response status is not 200, map response from server to display appropriate error messages
        // and if 200 get auth token and store it in local storage
        let {isUserValid, errors} = loginServerFormValidationMapper(responseStat, jsonResponse, jsonResponse.token)
        setIsUserValid(isUserValid);
        setErrors(errors);

        if(responseStat == 200) {
            if(jsonResponse.role == "ADOPTER") {
                router.push(ADOPTER_PROFILE_ROUTE)
            }else if (jsonResponse.role == "STAFF") {
                router.push(STAFF_PROFILE_ROUTE)
            }else{
                router.push(MANAGER_PROFILE_ROUTE)
            }
        }

    }


    return (

        <Box className={styles.container}>
            <Box className={styles.loginForm}>
                <TextField
                    className={styles.textArea}
                    label='Username'
                    placeholder='Enter your username/email'
                    inputRef={usernameRef}
                    required
                    variant="filled"
                    error={!isUserValid.username}
                    helperText={(isUserValid.username) ? "" : errors.username}
                    InputProps={{style: {background: "#FFF"}}}
                >
                </TextField>

                <TextField
                    className={styles.textArea}
                    label='Password'
                    type="password"
                    placeholder='Enter your password'
                    inputRef={passwordRef}
                    required
                    variant="filled"
                    error={!isUserValid.password}
                    helperText={(isUserValid.password) ? "" : errors.password}
                    InputProps={{style: {background: "#FFF"}}}
                >
                </TextField>

                <Button
                    className={styles.button}
                    variant="contained"
                    size="large"
                    onClick={handleSubmit}>
                    Sign In
                </Button>
                <Box>
                    OR
                    <span><br/><br/></span>
                </Box>
                <Box>
                    Don't have an account ?
                    <span>&nbsp;</span>
                    <Link href={SIGN_UP_ROUTE}>
                        Sign up
                    </Link>
                </Box>
            </Box>
        </Box>

    )
}

export default Page;