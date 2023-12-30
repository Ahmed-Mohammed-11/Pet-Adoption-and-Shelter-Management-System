'use client';
import {Autocomplete, Button, Card, CardActionArea, CardContent, CardMedia, Divider, Fade, FormControlLabel, FormLabel, Modal, Radio, RadioGroup, Stack, TextField, Typography, Box, ImageList, ImageListItem} from '@mui/material';
import styles from './page.module.css'
import PetsIcon from '@mui/icons-material/Pets';
import ChildFriendlyIcon from '@mui/icons-material/ChildFriendly';
import FingerprintIcon from '@mui/icons-material/Fingerprint';
import { Dispatch, SetStateAction, useState } from 'react';
import CloudUploadIcon from '@mui/icons-material/CloudUpload';
import { SaveOutlined } from '@mui/icons-material';
import {GET_USER_BACKEND_ENDPOINT} from "@/app/constants/apiConstants";
import getRequestController from "@/app/services/getRequestController";
import toJSON from "@/app/utils/readableStreamResponseBodytoJSON";
import postRequestController from "@/app/services/postRequestController";
import putRequest from "@/app/services/PutRequest";

const DEFAULT_IMAGE = "https://img.freepik.com/free-photo/cute-domestic-kitten-sits-window-staring-outside-generative-ai_188544-12519.jpg?size=626&ext=jpg&ga=GA1.1.1546980028.1702857600&semt=ais"

interface Props {
    pet: PetDTO,
    userType: string,
}

interface ModalProps {
    pet: PetDTO
    open: boolean,
    handleClose: Dispatch<SetStateAction<boolean>>
}

let options = new Map<string, number>([
    ["Playful", 0],
    ["Calm", 1],
    ["Curious", 2],
    ["Timid", 3],
    ["Affectionate", 4],
])

function PetCard(props: Props) {

    const pet = props.pet;

    const [modal, setModal] = useState(false);
    const [view, setView] = useState(false);

    return (
        <>
            <Card className={styles.card}>
                <CardActionArea>
                    <CardMedia
                        component="img"
                        alt="default image"
                        height="140"
                        image={DEFAULT_IMAGE}
                    />
                    <CardContent>
                        <Typography className={styles.card_header} gutterBottom fontSize={24}>
                            {pet.name}
                        </Typography>
                        <Typography color="gray" fontSize={16}>
                            <PetsIcon fontSize='inherit'/>
                            &nbsp;{pet.species}
                        </Typography>
                        <Divider sx={{margin: '0.5vh 0.5vw'}}/>
                        <Typography color="gray" fontSize={16}>
                            <FingerprintIcon fontSize='inherit'/>
                            &nbsp;{pet.breed}
                        </Typography>
                        <Divider sx={{margin: '0.5vh 0.5vw'}}/>
                        <Typography color="gray" fontSize={16}>
                            <ChildFriendlyIcon fontSize='inherit'/>
                            &nbsp;{pet.age} months
                        </Typography>
                    </CardContent>
                </CardActionArea>
                {(props.userType === "manager" || props.userType === "staff") &&
                    <Button onClick={() => setModal(true)}>
                    Edit Pet
                </Button>}
                {(props.userType === "adopter") &&
                    <Button onClick={() => setView(true)}>
                    View Pet
                </Button>}
            </Card>
            {(props.userType === "manager" || props.userType === "staff") && <EditPet open={modal} pet={pet} handleClose={setModal} />}
            {(props.userType === "adopter") && <ViewPet open={view} pet={pet} handleClose={setView} />}
        </>
    );
}

function EditPet(props : ModalProps) {

    const [gender, setGender] = useState(props.pet.gender);
    const [neutered, setNeutered] = useState(props.pet.healthStatus?.neutering);
    const [training, setTraining] = useState(props.pet.houseTraining);
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
        neutering: false,
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
    }


    const fetchResponse = async () => {

        // the two controllers as one with post request
        let url = "/staff/update/" + props.pet.petId
        let response = await putRequest.sendPutRequest(formData, url)

        // toJSON util to convert ReadableStream to JSON
        let jsonResponse = await toJSON(response.body!);
        let responseStat = response.status;

        if(responseStat == 200)
            console.log("Pet updated successfully")
        // setFormData(jsonResponse)
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
                            defaultValue={props.pet.name}
                            onChange={(e) => setFormData({...formData, name: e.target.value})}
                        />
                        <TextField
                            margin="dense"
                            label="Species"
                            variant="outlined"
                            defaultValue={props.pet.species}
                            onChange={(e) => setFormData({...formData, species: e.target.value})}
                        />
                        <TextField
                            margin="dense"
                            label="Breed"
                            variant="outlined"
                            defaultValue={props.pet.breed}
                            onChange={(e) => setFormData({...formData, breed: e.target.value})}
                        />
                        <TextField
                            margin="dense"
                            label="Age"
                            InputProps={{
                                endAdornment:"Months"
                            }}
                            variant="outlined"
                            defaultValue={props.pet.age}
                            onChange={(e) => setFormData({...formData, age: Number(e.target.value)})}
                        />
                        <FormLabel>Gender</FormLabel>
                        <RadioGroup
                            row
                            value={gender}
                            defaultValue={props.pet.gender}
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
                            defaultValue={props.pet.healthStatus?.neutering}
                            onChange={(e, newValue) => {
                                setNeutered(newValue === 'true')
                                setFormData({...formData, neutering: newValue === 'true'})
                            }}
                        >
                            <FormControlLabel value={true} control={<Radio />} label="Yes" />
                            <FormControlLabel value={false} control={<Radio />} label="No" />
                        </RadioGroup>
                        <FormLabel>House Trained?</FormLabel>
                        <RadioGroup
                            row
                            value={neutered}
                            defaultValue={props.pet.houseTraining}
                            onChange={(e, newValue) => {
                                setTraining(newValue === 'true')
                                setFormData({...formData, houseTraining: newValue === 'true'})
                            }}
                        >
                            <FormControlLabel value={true} control={<Radio />} label="Yes" />
                            <FormControlLabel value={false} control={<Radio />} label="No" />
                        </RadioGroup>
                        <Autocomplete
                            disablePortal
                            defaultValue={props.pet.behaviour?.toString()}
                            options={Array.from(options.keys())}
                            renderInput={(params) => <TextField {...params} label="Behavior" />}
                            onChange={(e : any, newValue : string | null) => {setFormData({...formData, behaviour: Number(options.get(newValue!))})}}
                        />
                        <TextField
                            margin="dense"
                            label="description"
                            variant="outlined"
                            defaultValue={props.pet.description}
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
                            <SaveOutlined/> Save pet
                        </Button>
                    </Stack>
                </Fade>
            </Modal>
    );
}

function ViewPet(props : ModalProps) {

    const [gender, setGender] = useState(props.pet.gender);
    const [neutered, setNeutered] = useState(props.pet.healthStatus?.neutering);
    const [training, setTraining] = useState(props.pet.houseTraining);
    const [imageUrl, setImageUrl] = useState('');
    const [imageFile, setImageFile] = useState('');

    let images = []

    const addAdoption = () => {

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
                    <Stack className={styles.modalStyle} alignItems={'left'} width={'70%'}>
                        <Typography variant='h4' marginBottom={'1vh'}>View Pet</Typography>
                        <Stack direction={'row'} margin={'1vh auto'}>
                            <Box margin={'2vh 2vw'}>
                                <Stack direction={'row'}>
                                    <Typography>Name :</Typography>&nbsp;<Typography color={'GrayText'}>{props.pet.name}</Typography>
                                </Stack>
                                <Stack direction={'row'}>
                                    <Typography>Species :</Typography>&nbsp;<Typography color={'GrayText'}>{props.pet.species}</Typography>
                                </Stack>
                                <Stack direction={'row'}>
                                    <Typography>Breed :</Typography>&nbsp;<Typography color={'GrayText'}>{props.pet.breed}</Typography>
                                </Stack>
                                <Stack direction={'row'}>
                                    <Typography>Age :</Typography>&nbsp;<Typography color={'GrayText'}>{props.pet.age}&nbsp;months</Typography>
                                </Stack>
                                <Stack direction={'row'}>
                                    <Typography>Gender :</Typography>&nbsp;<Typography color={'GrayText'}>{props.pet.gender}</Typography>
                                </Stack>
                                <Stack direction={'row'}>
                                    <Typography>House trained? :</Typography>&nbsp;<Typography color={'GrayText'}>{props.pet.houseTraining}</Typography>
                                </Stack>
                                <Stack direction={'row'}>
                                    <Typography>Neutered? :</Typography>&nbsp;<Typography color={'GrayText'}>{props.pet.healthStatus?.neutering}</Typography>
                                </Stack>
                                <Stack direction={'row'}>
                                    <Typography>Vaccinations :</Typography>&nbsp;<Typography color={'GrayText'}>{props.pet.healthStatus?.vaccinations.toString()}</Typography>
                                </Stack>
                                <Stack direction={'row'}>
                                    <Typography>Behavior :</Typography>&nbsp;<Typography color={'GrayText'}>{props.pet.behaviour}</Typography>
                                </Stack>
                                <Button
                                    variant='contained'
                                    sx={{
                                        margin: '2vh 0 0',
                                    }}
                                    onClick={addAdoption}
                                >
                                    Apply For Adoption
                                </Button>
                            </Box>
                            <Box margin={'2vh 2vw'}>{ props.pet.images &&
                            <ImageList sx={{ width: 500, height: 450 }} cols={3} rowHeight={164}>
                                {props.pet.images.map((img, index) => (
                                    <ImageListItem key={index}>
                                    <img
                                        src={`${img}`}
                                        alt={props.pet.name}
                                        loading="lazy"
                                    />
                                    </ImageListItem>
                                ))}
                            </ImageList>}
                            </Box>
                        </Stack>
                    </Stack>
                </Fade>
            </Modal>
    );
}

export default PetCard;

