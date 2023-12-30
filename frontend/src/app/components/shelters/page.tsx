'use client';
import styles from './page.module.css'
import {Box, Stack} from "@mui/system";
import {Button, Fab, Fade, Grid, Modal, Paper, TextField, Typography} from "@mui/material";
import {Dispatch, SetStateAction, useEffect, useState} from "react";
import SideBar from "@/app/components/side-bar/side-bar";
import ShelterCard from "../shelter-card/shelter-card";
import AddIcon from '@mui/icons-material/Add';
import { MuiTelInput } from 'mui-tel-input';
import {ADD_PETS_BACKEND_ENDPOINT, GET_PETS_BACKEND_ENDPOINT, GET_SHELTER_ENDPOINT} from "@/app/constants/apiConstants";
import getRequestController from "@/app/services/getRequestController";
import toJSON from "@/app/utils/readableStreamResponseBodytoJSON";
import postRequestController from "@/app/services/postRequestController";

interface Props {
    itemList: any,
    // shelters: ShelterDTO[],
    userType: string,
}

interface ModalProps {
    open: boolean,
    handleClose: Dispatch<SetStateAction<boolean>>,
    onAddShelter: Function,
}

function Shelters(props: Props) {

    const [modal, setModal] = useState(false);
    const [shelters, setShelters] = useState<ShelterDTO[]>([]);
    const addShelter = (shelter : ShelterDTO) => {
        shelters.push(shelter);
    }

    useEffect(() => {
        fetchResponse();
    }, []);

    const fetchResponse = async () => {
        // the two controllers as one with post request
        let url = GET_SHELTER_ENDPOINT;

        let response = await getRequestController.sendGetRequest(url);

        // toJSON util to convert ReadableStream to JSON
        let jsonResponse = await toJSON(response.body!);
        let responseStat = response.status;
        console.log(jsonResponse)

        setShelters(jsonResponse)
    }


    return (
        // SideBar
        <>
            <CreateShelter open={modal} handleClose={setModal} onAddShelter={addShelter} />
            <Box display="flex" alignItems="stretch" justifyContent="center" height="fit">
                <SideBar width={"20%"} userType={props.userType} itemList={props.itemList}/>

                <Paper style={{padding: '2vh 2vw', width: '80%', height: 'fit'}}>
                    <Typography variant="h3" gutterBottom color="primary">
                        Shelters List
                    </Typography>
                    <Grid
                        container
                        direction="row"
                        rowGap={3}
                        columnGap={2}
                        justifyContent="space-around"
                        alignItems="flex-start"
                        margin={'2vh auto'}
                        padding={'2vh 2vw'}
                    >
                        {shelters.map((shelter: ShelterDTO) => (
                            <ShelterCard shelter={shelter} userType={props.userType} />
                        ))}
                    </Grid>
                </Paper>
                <Fab color="secondary" sx={{ position: 'fixed', bottom: 32, right: 32 }} onClick={() => setModal(true)}>
                    <AddIcon />
                </Fab>
            </Box>
        </>
    );
}

function CreateShelter(props : ModalProps) {

    const [phone, setPhone] = useState('');

    const handlePhone = (e : string) => {
        setPhone(e);
        setFormData({...formData, phone: e})
    }

    const [formData, setFormData] = useState({
        name: '',
        location: '',
        phone: '',
        email: ''
    });

    const addShelter = () => {
        console.log(formData);
        fetchResponse();
        props.handleClose(false);
        props.onAddShelter(formData);
    }

    useEffect(() => {
        fetchResponse();
    }, []);

    const fetchResponse = async () => {

        // the two controllers as one with post request
        let url = "/manager/shelter/create"
        let response = await postRequestController.sendPostRequest(formData, url);

        // toJSON util to convert ReadableStream to JSON
        let jsonResponse = await toJSON(response.body!);
        let responseStat = response.status;
        console.log(jsonResponse)
        if(responseStat === 200) {
            console.log("Shelter added successfully")
        }
    }


    return (
        <Modal
            open={props.open}
            onClose={() => props.handleClose(false)}
            aria-labelledby="modal-title"
            aria-describedby="modal-description"
            closeAfterTransition
            slotProps={{
                backdrop: {
                timeout: 300,
                },
            }}
            >
                <Fade in={props.open}>
                    <Stack className={styles.modalStyle} alignItems={'left'} width={'480px'}>
                        <Typography variant='h4' marginBottom={'1vh'}>Add new Shelter</Typography>
                        <TextField
                            margin="normal"
                            label="Name"
                            name="name"
                            variant="outlined"
                            onChange={(e) => setFormData({...formData, name: e.target.value})}
                        />
                        <TextField
                            margin="normal"
                            label="Location"
                            name="location"
                            variant="outlined"
                            onChange={(e) => setFormData({...formData, location: e.target.value})}
                        />
                        <MuiTelInput 
                            value={phone}
                            onChange={handlePhone}
                            defaultCountry="EG"
                            margin="normal"
                        />
                        <TextField
                            margin="normal"
                            label="E-mail"
                            name="email"
                            type='email'
                            variant="outlined"
                            onChange={(e) => setFormData({...formData, email: e.target.value})}
                        />
                        <Button
                            variant='contained'
                            sx={{
                                margin: '2vh 0 0',
                            }}
                            onClick={addShelter}
                        >
                            Add shelter
                        </Button>
                    </Stack>
                </Fade>
            </Modal>
    );
}

export default Shelters;