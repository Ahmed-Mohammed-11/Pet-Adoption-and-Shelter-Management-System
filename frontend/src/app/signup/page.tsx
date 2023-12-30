'use client';
import styles from './page.module.css'
import {Button, FormControlLabel, Link, Radio, RadioGroup, TextField} from "@mui/material";
import {Box} from "@mui/system";
import {useRef, useState} from "react";
import signupController from "@/app/services/signupController";
import {
    ADOPTER_PROFILE_ROUTE, MANAGER_PROFILE_ROUTE,
    SIGN_IN_ROUTE,
    SIGN_UP_ADOPTER_BACKEND_ENDPOINT,
    SIGN_UP_MANAGER_BACKEND_ENDPOINT, SIGN_UP_STAFF_BACKEND_ENDPOINT, STAFF_PROFILE_ROUTE,
} from "@/app/constants/apiConstants";
import clientValidateForm from "@/app/security/userValidation/clientFormValidation";
import toJSON from "@/app/utils/readableStreamResponseBodytoJSON";
import {useRouter} from "next/navigation";
import {setReferenceManifestsSingleton} from "next/dist/server/app-render/action-encryption-utils";
import signupServerFormValidationMapper from "@/app/security/userValidation/signupServerFormValidationMapper";

function Page() {
    const usernameRef = useRef<HTMLInputElement>(null);
    const emailRef = useRef<HTMLInputElement>(null);
    const passwordRef = useRef<HTMLInputElement>(null);
    const phoneRef = useRef<HTMLInputElement>(null);
    const firstNameRef = useRef<HTMLInputElement>(null);
    const lastNameRef = useRef<HTMLInputElement>(null);
    const shelterNameRef = useRef<HTMLInputElement>(null);
    const staffRoleRef = useRef<HTMLInputElement>(null);

    const [isUserValid, setIsUserValid] = useState({
        username: true,
        email: true,
        password: true
    });

    const [errors, setErrors] = useState({
        username: "",
        email: "",
        password: ""
    });

    const [selectedRole, setSelectedRole] = useState("adopter");


    const router = useRouter();

    const handleSubmit = () => {
        // user credentials
        let user: UserDTO = {
            username: usernameRef.current!.value,
            email: emailRef.current!.value,
            password: passwordRef.current!.value,
            role: selectedRole,
            phone: phoneRef.current!.value,
            firstName: firstNameRef.current!.value,
            lastName: lastNameRef.current!.value,
            shelterName: shelterNameRef.current?.value,
            staffRole: staffRoleRef.current?.value
        }

        // validate user credentials on client side
        let {isUserValid, errors} = clientValidateForm(user)
        setIsUserValid(isUserValid)
        setErrors(errors);

        // if user credentials are valid, try sending to server
        isUserValid.username && isUserValid.email && isUserValid.password && sendInfoToServer(user);
    }

    const handleRoleChange = (event: any) => {
        setSelectedRole(event.target.value);
    };


    async function sendInfoToServer(user: UserDTO) {
        // prepare user data to send to server
        let userDTO: UserDTO = {
            username: user.username,
            email: user.email,
            password: user.password,
            role: user.role,
            phone: user.phone,
            firstName: user.firstName,
            lastName: user.lastName,
            shelterName: user?.shelterName,
            staffRole: user?.staffRole
        }
        //not sure about this await
        await fetchResponse(userDTO);
    }

    const fetchResponse = async (userDTO: UserDTO) => {
        let response: Response;
        if(userDTO.role == "adopter") {
            console.log("Adopter request sent")
            response = await signupController.sendPostRequest(userDTO, SIGN_UP_ADOPTER_BACKEND_ENDPOINT);
        } else if (userDTO.role == "staff") {
            console.log("Staff request sent")
            response = await signupController.sendPostRequest(userDTO, SIGN_UP_STAFF_BACKEND_ENDPOINT);
        } else if (userDTO.role == "manager") {
            console.log("manager request sent")
            response = await signupController.sendPostRequest(userDTO, SIGN_UP_MANAGER_BACKEND_ENDPOINT);
        } else {
            throw new Error("Invalid user role");
        }

        // toJSON util to convert ReadableStream to JSON
        let jsonResponse = await toJSON(response.body!);
        let responseStat = response.status;

        //if response status is 200, redirect to home page
        console.log("sign up response: " + jsonResponse.status)
        //if response status is not 200, map response from server to display appropriate error messages
        //and if 200 get auth token and store it in local storage

        let {isUserValid, errors} = signupServerFormValidationMapper(responseStat, jsonResponse, jsonResponse)
        setIsUserValid(isUserValid);

        setErrors(errors);
        if(responseStat == 200) {
            router.push(SIGN_IN_ROUTE)
        }
    }


    return (
        <Box className={styles.container}>
            <Box className={styles.signupForm}>
                <TextField
                    className={styles.textArea}
                    label='Username'
                    placeholder='Pick a username'
                    inputRef={usernameRef}
                    required
                    size={"small"}
                    variant="outlined"
                    error={!isUserValid.username}
                    helperText={(isUserValid.username) ? "" : errors.username}
                    InputProps={{style: {background: "#FFF"}}}
                >
                </TextField>

                <TextField
                    className={styles.textArea}
                    label='Email' type="email"
                    placeholder='Email'
                    inputRef={emailRef}
                    required
                    size={"small"}
                    variant="outlined"
                    error={!isUserValid.email}
                    helperText={(isUserValid.email) ? "" : errors.email}
                    InputProps={{style: {background: "#FFF"}}}
                >
                </TextField>
                <TextField
                    className={styles.textArea}
                    label='Password'
                    type="password"
                    placeholder='pick a password'
                    inputRef={passwordRef}
                    required
                    size={"small"}
                    variant="outlined"
                    // error={!isUserValid.password}
                    helperText={(isUserValid.password) ? "Make it strong" : errors.password}
                    InputProps={{style: {background: "#FFF"}}}
                >
                </TextField>
                <TextField
                    className={styles.textArea}
                    label='phone number'
                    placeholder='phone number'
                    inputRef={phoneRef}
                    required
                    size={"small"}
                    variant="outlined"
                    // error={!isUserValid.password}
                    // helperText={(isUserValid.password) ? "Make it strong" : errors.password}
                    InputProps={{style: {background: "#FFF"}}}
                >
                </TextField>
                <TextField
                    className={styles.textArea}
                    label='First Name'
                    placeholder='first name'
                    inputRef={firstNameRef}
                    required
                    size={"small"}
                    variant="outlined"
                    // error={!isUserValid.password}
                    // helperText={(isUserValid.password) ? "Make it strong" : errors.password}
                    InputProps={{style: {background: "#FFF"}}}
                >
                </TextField>
                <TextField
                    className={styles.textArea}
                    label='Last Name'
                    placeholder='Last name'
                    inputRef={lastNameRef}
                    required
                    size={"small"}
                    variant="outlined"
                    // error={!isUserValid.password}
                    // helperText={(isUserValid.password) ? "Make it strong" : errors.password}
                    InputProps={{style: {background: "#FFF"}}}
                >
                </TextField>
                {selectedRole=="staff" &&
                    <TextField
                        className={styles.textArea}
                        label='Shelter Name'
                        placeholder='shelter name'
                        inputRef={shelterNameRef}
                        required
                        size={"small"}
                        variant="outlined"
                        // error={!isUserValid.password}
                        // helperText={(isUserValid.password) ? "Make it strong" : errors.password}
                        InputProps={{style: {background: "#FFF"}}}
                    >
                    </TextField>
                }
                {selectedRole=="staff" &&
                    <TextField
                        className={styles.textArea}
                        label='Staff Role'
                        placeholder='Staff Role'
                        inputRef={staffRoleRef}
                        required
                        size={"small"}
                        variant="outlined"
                        // error={!isUserValid.password}
                        // helperText={(isUserValid.password) ? "Make it strong" : errors.password}
                        InputProps={{style: {background: "#FFF"}}}
                    >
                    </TextField>
                }

                <RadioGroup
                    aria-label="role"
                    name="role"
                    value={selectedRole}
                    onChange={handleRoleChange}
                    row
                >
                    <FormControlLabel value="adopter" control={<Radio />} label="Adopter" />
                    <FormControlLabel value="staff" control={<Radio />} label="Staff" />
                    <FormControlLabel value="manager" control={<Radio />} label="Manager" />
                </RadioGroup>

                <Button
                    className={styles.button}
                    variant="contained"
                    size="large"
                    onClick={handleSubmit}>
                    Create Account
                </Button>

                <Box>
                    OR
                    <span><br/></span>
                </Box>

                <Box>
                    Already have an account ?
                    <span>&nbsp;</span>
                    <Link href={SIGN_IN_ROUTE}>
                        Sign in
                    </Link>
                </Box>
            </Box>

        </Box>
    )
}

export default Page;