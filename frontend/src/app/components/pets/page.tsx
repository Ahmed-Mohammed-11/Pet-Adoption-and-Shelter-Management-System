'use client';
import styles from './page.module.css'
import {Box, Stack, Button, Fab, Fade, FormControlLabel, FormLabel, Grid, Modal, Paper, Radio, RadioGroup, TextField, Typography, Autocomplete} from "@mui/material";
import {Dispatch, SetStateAction, useEffect, useState} from "react";
import SideBar from "@/app/components/side-bar/side-bar";
import PetCard from "../pet-card/pet-card";
import AddIcon from '@mui/icons-material/Add';
import CloudUploadIcon from '@mui/icons-material/CloudUpload';
import {
    ADD_PETS_BACKEND_ENDPOINT,
    GET_PETS_BACKEND_ENDPOINT,
    GET_USER_BACKEND_ENDPOINT
} from "@/app/constants/apiConstants";
import getRequestController from "@/app/services/getRequestController";
import toJSON from "@/app/utils/readableStreamResponseBodytoJSON";
import postRequestController from "@/app/services/postRequestController";

let options = new Map<string, number>([
    ["Playful", 0],
    ["Calm", 1],
    ["Curious", 2],
    ["Timid", 3],
    ["Affectionate", 4],
])

interface Props {
    itemList: any,
    // pets: PetDTO[],
    userType: string,
}

interface ModalProps {
    open: boolean,
    handleClose: Dispatch<SetStateAction<boolean>>,
    onAddPet: Function,
}

function Pets(props: Props) {

    const [modal, setModal] = useState(false); 
    const [pets, setPets] = useState<PetDTO[]>([]);
    const addPet = (pet : PetDTO) => {
        pets.push(pet);
    }

    useEffect(() => {
        fetchResponse();
    }, []);

    const fetchResponse = async () => {
        // the two controllers as one with post request
        let url = GET_PETS_BACKEND_ENDPOINT
        if(props.userType == "staff") {
            url = "/staff/pets?pageNumber=1"
        } else {
            url = "/adopter/pets?pageNumber=1"
        }
        let response = await getRequestController.sendGetRequest(url);

        // toJSON util to convert ReadableStream to JSON
        let jsonResponse = await toJSON(response.body!);
        let responseStat = response.status;
        console.log(jsonResponse)
        setPets(jsonResponse)
    }


    return (
        // SideBar
        <>
            {props.userType=="staff" &&
                <CreatePet open={modal} handleClose={setModal} onAddPet={addPet} />
            }

            <Box display="flex" alignItems="stretch" justifyContent="center" height="fit">
                <SideBar width={"20%"} userType={props.userType} itemList={props.itemList}/>

                <Paper style={{padding: '2vh 2vw', width: '80%', height: 'fit'}}>
                    <Typography variant="h3" gutterBottom color="primary">
                        Pets List
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
                        {pets.map((pet: PetDTO) => (
                            <PetCard pet={pet} userType={props.userType}/>
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

function CreatePet(props : ModalProps) {

    const [gender, setGender] = useState('male');
    const [neutered, setNeutered] = useState(false);
    const [vaccinated, setVaccinated] = useState(false);
    const [training, setTraining] = useState(false);
    const [imageUrl, setImageUrl] = useState('');
    const [imageFile, setImageFile] = useState('');

    let images = []

    const [formData, setFormData] = useState({
        name: '',
        species: '',
        age: -1,
        gender: '',
        description: '',
        breed: '',
        houseTraining: false,
        behaviour: -1,
        shelterId: -1,
        isFertilised: false,
        isVaccinated: false,
    });

    const chooseImage = (e : any) => {
        const file = e.target.files[0];
        setImageUrl(URL.createObjectURL(file));
        setImageFile(file);
        images.push(imageFile);
    }

    const addPet = () => {
        console.log(formData);
        fetchResponse()
        props.handleClose(false);
        props.onAddPet(formData);
    }

    useEffect(() => {
        fetchResponse();
    }, []);

    const fetchResponse = async () => {

        // the two controllers as one with post request
        let url = "/staff/create"
        let response = await postRequestController.sendPostRequest(formData, url);

        // toJSON util to convert ReadableStream to JSON
        let jsonResponse = await toJSON(response.body!);
        let responseStat = response.status;
        console.log(jsonResponse)
        if(responseStat === 200) {
            console.log("Pet added successfully")
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
                        <Typography variant='h4' marginBottom={'1vh'}>Add new Pet</Typography>
                        <TextField
                            margin="dense"
                            label="Name"
                            variant="outlined"
                            onChange={(e) => setFormData({...formData, name: e.target.value})}
                        />
                        <TextField
                            margin="dense"
                            label="Species"
                            variant="outlined"
                            onChange={(e) => setFormData({...formData, species: e.target.value})}
                        />
                        <TextField
                            margin="dense"
                            label="Breed"
                            variant="outlined"
                            onChange={(e) => setFormData({...formData, breed: e.target.value})}
                        />
                        <TextField
                            margin="dense"
                            label="Age"
                            InputProps={{
                                endAdornment:"Months"
                            }}
                            variant="outlined"
                            onChange={(e) => setFormData({...formData, age: Number(e.target.value)})}
                        />
                        <FormLabel>Gender</FormLabel>
                        <RadioGroup
                            row
                            value={gender}
                            onChange={(e, newValue) => {
                                setGender(newValue)
                                setFormData({...formData, gender: newValue})
                            }}
                        >
                            <FormControlLabel value="female" control={<Radio />} label="Female" />
                            <FormControlLabel value="male" control={<Radio />} label="Male" />
                        </RadioGroup>
                        <FormLabel>Neutered?</FormLabel>
                        <RadioGroup
                            row
                            value={neutered}
                            onChange={(e, newValue) => {
                                setNeutered(newValue === 'true')
                                setFormData({...formData, isFertilised: newValue === 'true'})
                            }}
                        >
                            <FormControlLabel value={true} control={<Radio />} label="Yes" />
                            <FormControlLabel value={false} control={<Radio />} label="No" />
                        </RadioGroup>
                        <FormLabel>House Trained?</FormLabel>
                        <RadioGroup
                            row
                            value={neutered}
                            onChange={(e, newValue) => {
                                setTraining(newValue === 'true')
                                setFormData({...formData, houseTraining: newValue === 'true'})
                            }}
                        >
                            <FormControlLabel value={true} control={<Radio />} label="Yes" />
                            <FormControlLabel value={false} control={<Radio />} label="No" />
                        </RadioGroup>
                        <FormLabel>vaccinated?</FormLabel>
                        <RadioGroup
                            row
                            value={vaccinated}
                            onChange={(e, newValue) => {
                                setVaccinated(newValue === 'true')
                                setFormData({...formData, isVaccinated: newValue === 'true'})
                            }}
                        >
                            <FormControlLabel value={true} control={<Radio />} label="Yes" />
                            <FormControlLabel value={false} control={<Radio />} label="No" />
                        </RadioGroup>


                        <Autocomplete
                            disablePortal
                            options={Array.from(options.keys())}
                            renderInput={(params) => <TextField {...params} label="Behavior" />}
                            onChange={(e : any, newValue : string | null) => {setFormData({...formData, behaviour: Number(options.get(newValue!))})}}
                        />
                        <TextField
                            margin="dense"
                            label="description"
                            variant="outlined"
                            onChange={(e) => setFormData({...formData, description: e.target.value})}
                        />
                        <Button component="label" variant="contained" startIcon={<CloudUploadIcon></CloudUploadIcon>}>
                            Upload Images
                        <input className={styles.VisuallyHiddenInput} type="file" onChange={chooseImage}/>
                        </Button>
                        <Button
                            variant='contained'
                            sx={{
                                margin: '2vh 0 0',
                            }}
                            onClick={addPet}
                        >
                            Add pet
                        </Button>
                    </Stack>
                </Fade>
            </Modal>
    );
}

export default Pets;