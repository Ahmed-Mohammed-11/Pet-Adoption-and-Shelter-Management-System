'use client';


import {Box, Button, Paper, TextField, Typography} from "@mui/material";
import {useState} from "react";
import styles from './page.module.css';
import SideBar from "@/app/components/side-bar/side-bar";
import putRequest from "@/app/services/PutRequest";
import toJSON from "@/app/utils/readableStreamResponseBodytoJSON";
import postRequestController from "@/app/services/postRequestController";

function Page() {

    const [formData, setFormData] = useState(-1);
    const [isAvailable, setIsAvailable] = useState(true);
    const [sendSuccessfully, setSendSuccessfully] = useState(false);

    //request to adopt a pet and check if available

    const handleSubmit = () => {
        fetchResponse()
    }

    const fetchResponse = async () => {

        // the two controllers as one with post request
        let url = "/adopter/adopt?petId=" + formData
        console.log("url is " + url)
        let response = await postRequestController.sendPostRequest({}, url)

        // toJSON util to convert ReadableStream to JSON
        let jsonResponse = await toJSON(response.body!);
        let responseStat = response.status;

        if(responseStat == 200) {
            setSendSuccessfully(true)
            console.log("adoption successfully")
        }
        // setFormData(jsonResponse)
    }

    return (
        <Box display="flex" alignItems="stretch" justifyContent="center" height="fit">
            <SideBar width={"20%"} itemList={[{text: "profile"}, {text: "pets"}, {text: "applyForAdoption"}]}
                     userType={"adopter"}/>
            <Paper style={{padding: '2vh 2vw', width: '80%', height: '100vh'}}>
                <Typography className={styles.header} variant="h3" gutterBottom color="primary">
                    Apply For Adoption
                </Typography>
                <Box className={styles.page}>
                    {sendSuccessfully && (<Typography
                            className={styles.success}
                            variant="h5"
                        >
                            Application Sent Successfully!
                        </Typography>
                    )}
                    <TextField
                        label="Pet ID to adopt"
                        variant="outlined"
                        margin="normal"
                        fullWidth
                        error={!isAvailable}
                        helperText={(isAvailable) ? "Enter the ID of the pet you want to adopt" : "This pet is not available for adoption"}
                        onChange={(e) => setFormData(parseInt(e.target.value, 10))}
                    />

                    <Button
                        variant="contained"
                    >
                        Apply For Adoption
                        onclick={handleSubmit()}
                    </Button>
                </Box>
            </Paper>
        </Box>
    );
}

export default Page;

