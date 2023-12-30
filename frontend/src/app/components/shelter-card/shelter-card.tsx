'use client';
import {Button, Card, CardActionArea, CardContent, CardMedia, Divider, Fade, Modal, Typography, Stack, TextField, List, ListItem, ListItemButton, ListItemAvatar, Avatar, ListItemText, IconButton} from '@mui/material';
import styles from './page.module.css'
import BusinessIcon from '@mui/icons-material/Business';
import AlternateEmailIcon from '@mui/icons-material/AlternateEmail';
import LocalPhoneIcon from '@mui/icons-material/LocalPhone';
import { Dispatch, SetStateAction, useState } from 'react';
import { MuiTelInput } from 'mui-tel-input';
import { SaveOutlined } from '@mui/icons-material';
import ThumbUpAltIcon from '@mui/icons-material/ThumbUpAlt';

const DEFAULT_IMAGES = [
    "https://media.istockphoto.com/id/1314520580/vector/homeless-animals-people-in-shelter-with-pet-cats-and-dogs-in-cages-vector-concept.jpg?s=612x612&w=0&k=20&c=5wEsYW_lNNnS6vJj5m6ebNljzzAdcZM6zEG9PEfE92A=",
    "https://img.freepik.com/free-vector/happy-volunteers-with-badges-working-animal-shelter-taking-care-about-homeless-cats-dogs-cages-vector-illustration-adopting-pet-animal-care-concept_74855-13124.jpg",
    "https://static.vecteezy.com/system/resources/previews/009/951/691/original/animal-shelter-cartoon-illustration-with-pets-sitting-in-cages-and-volunteers-feeding-animals-for-adopting-in-flat-hand-drawn-style-design-vector.jpg",
    "https://img.freepik.com/free-vector/animal-shelter-horizontal-illustration-with-pets-sitting-cages-volunteers-feeding-animals-flat_1284-32955.jpg",
    "https://thumbs.dreamstime.com/b/family-adopting-pet-animal-shelter-flat-cartoon-vector-illustration-banner-pets-shelter-interior-volunteers-animals-196377441.jpg",
]

interface Props {
    shelter: ShelterDTO,
    userType: string,
}

interface ModalProps {
    shelter: ShelterDTO,
    open: boolean,
    handleClose: Dispatch<SetStateAction<boolean>>,
}

interface StaffListProps {
    shelterId: number | undefined,
    open: boolean,
    handleClose: Dispatch<SetStateAction<boolean>>,
}

function ShelterCard(props: Props) {

    const shelter = props.shelter;
    const imgIndex = Math.floor(Math.random() * 5);

    const [modal, setModal] = useState(false);
    const [staffList, setStaffList] = useState(false);

    return (
        <>
            <Card className={styles.card}>
                <CardActionArea>
                    <CardMedia
                        component="img"
                        alt="default image"
                        height="140"
                        image={DEFAULT_IMAGES[imgIndex]}
                    />
                    <CardContent>
                        <Typography className={styles.card_header} gutterBottom fontSize={24}>
                            {shelter.name}
                        </Typography>
                        <Typography color="gray" fontSize={16}>
                            <BusinessIcon fontSize='inherit'/>
                            &nbsp;{shelter.location}
                        </Typography>
                        <Divider sx={{margin: '0.5vh 0.5vw'}}/>
                        <Typography color="gray" fontSize={16}>
                            <LocalPhoneIcon fontSize='inherit'/>
                            &nbsp;{shelter.phone}
                        </Typography>
                        <Divider sx={{margin: '0.5vh 0.5vw'}}/>
                        <Typography color="gray" fontSize={16}>
                            <AlternateEmailIcon fontSize='inherit'/>
                            &nbsp;{shelter.email}
                        </Typography>
                    </CardContent>
                </CardActionArea>
                { props.userType === "manager" &&
                    <>
                        <Button onClick={() => setModal(true)}>
                            Manage Shelter
                        </Button>
                        <Button onClick={() => setStaffList(true)}>
                            Add Staff
                        </Button>
                    </>
                }
            </Card>
            {props.userType === "manager" && <EditShelter shelter={shelter} open={modal} handleClose={setModal} />}
            {props.userType === "manager" && <StaffList shelterId={shelter.shelterId} open={staffList} handleClose={setStaffList} />}
        </>
    );
}

function EditShelter(props : ModalProps) {

    const [phone, setPhone] = useState(props.shelter.phone);

    const handlePhone = (e : string) => {
        setPhone(e);
        setFormData({...formData, phone: e})
    }

    const [formData, setFormData] = useState({
        name: props.shelter.name,
        location: props.shelter.location,
        phone: props.shelter.phone,
        email: props.shelter.email
    });

    const editShelter = () => {
        console.log(formData);
        props.handleClose(false);
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
                        <Typography variant='h4' marginBottom={'1vh'}>Edit Shelter</Typography>
                        <TextField
                            margin="normal"
                            label="Name"
                            name="name"
                            variant="outlined"
                            defaultValue={props.shelter.name}
                            onChange={(e) => setFormData({...formData, name: e.target.value})}
                        />
                        <TextField
                            margin="normal"
                            label="Location"
                            name="location"
                            variant="outlined"
                            defaultValue={props.shelter.location}
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
                            defaultValue={props.shelter.email}
                            onChange={(e) => setFormData({...formData, email: e.target.value})}
                        />
                        <Button
                            variant='contained'
                            sx={{
                                margin: '2vh 0 0',
                            }}
                            onClick={editShelter}
                        >
                            <SaveOutlined/> Save
                        </Button>
                    </Stack>
                </Fade>
            </Modal>
    );
}

function StaffList (props : StaffListProps) {

    const fakeStaffList = [
        {
            id: 1,
            firstName: 'Fake',
            lastName: 'Staff',
            email: 'fake@example.com',
        },
    ];

    const addStaff = (id: number) => {
        // Add the staff (you have shleter ID in the props and staff ID in the parameter)
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
                <Stack className={styles.modalStyle} alignItems={'left'} width={'960px'}>
                    <Typography variant='h4' marginBottom={'1vh'}>Add Staff</Typography>
                    {/*Here we get all staff list */}
                    <List>
                    {
                        fakeStaffList.map((staff) => (
                            <ListItem sx={{ width: '100%'}}
                        key={staff.id}
                    >
                        <ListItemButton>
                        <ListItemAvatar>
                            <Avatar/>
                        </ListItemAvatar>
                        <ListItemText id={staff.id.toString()} primary={`Name : ${staff.firstName + " " + staff.lastName}, Email: ${staff.email}.`} />
                        </ListItemButton>

                        <IconButton onClick={() => addStaff(staff.id)}>
                            <ThumbUpAltIcon color='success'/>
                        </IconButton>
                    </ListItem>
                        ))
                    }
                    </List>

                </Stack>
            </Fade>
        </Modal>
    );
    
}

export default ShelterCard;

