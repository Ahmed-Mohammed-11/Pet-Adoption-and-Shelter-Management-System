'use client';
import {Autocomplete, Button, Card, CardActionArea, CardContent, CardMedia, Divider, Fade, FormControlLabel, FormLabel, Modal, Radio, RadioGroup, Stack, TextField, Typography, Box, ImageList, ImageListItem, Paper, List, ListItem, ListItemText, ListItemButton, ListItemAvatar, Avatar, IconButton} from '@mui/material';
import styles from './page.module.css'
import PetsIcon from '@mui/icons-material/Pets';
import ChildFriendlyIcon from '@mui/icons-material/ChildFriendly';
import FingerprintIcon from '@mui/icons-material/Fingerprint';
import { Dispatch, SetStateAction, useState } from 'react';
import CloudUploadIcon from '@mui/icons-material/CloudUpload';
import { SaveOutlined } from '@mui/icons-material';
import ApplicationDTO from '@/app/entities/dtos/ApplicationDTO';
import ThumbUpAltIcon from '@mui/icons-material/ThumbUpAlt';
import ThumbDownAltIcon from '@mui/icons-material/ThumbDownAlt';
import SideBar from '../side-bar/side-bar';

interface Props {
    shelterId: number,
    itemList: any,
    userType: string,
}

function Applications (props : Props) {

    // Here we get the applications from the backend

    const fakeApplications : ApplicationDTO[] = [
        {
            adopterName: "Ahmed",
            adopterUserId: 1,
            petName: "Simba",
            petId: 5,
        },
        {
            adopterName: "Ali",
            adopterUserId: 1,
            petName: "Lucky",
            petId: 5,
        },
        {
            adopterName: "Tammam",
            adopterUserId: 1,
            petName: "Bosy",
            petId: 5,
        },
    ]

    const handleReq = (adopterId: number, status: boolean) => {
        // Sent reply to request to the backend
    }

    return (
        <>
        <Box display="flex" alignItems="stretch" justifyContent="center" height="fit">
            <SideBar width={"20%"} userType={props.userType} itemList={props.itemList}/>
            <Paper style={{padding: '2vh 2vw', width: '80%', height: 'fit'}}>
            <Typography variant="h4" sx={{margin: '5vh 1vw'}}>
                Promotion Requests
            </Typography>
            <Paper elevation={5} sx={{width: '50vw', margin: 'auto 0'}}> 
            <List dense sx={{ width: '100%', bgcolor: 'background.paper', alignItems:'auto', margin:'auto' }}>
                {(fakeApplications.toString() === "") ?
                    <ListItem>
                        <ListItemText>You have no requests to review</ListItemText>
                    </ListItem>
                    :
                    fakeApplications.map((req) => 
                        <ListItem sx={{ width: '100%'}}
                            key={req.adopterUserId}
                        >
                            <ListItemButton>
                            <ListItemAvatar>
                                <Avatar
                                    alt={req.adopterName}
                                />
                            </ListItemAvatar>
                            <ListItemText id={req.adopterUserId.toString()} primary={`${req.adopterName} wants to adopt ${req.petName}`} />
                            </ListItemButton>

                            <IconButton onClick={() => handleReq(req.adopterUserId, true)}>
                                <ThumbUpAltIcon color='success'/>
                            </IconButton>
                            <IconButton onClick={() => handleReq(req.adopterUserId, false)}>
                                <ThumbDownAltIcon color='error'/>
                            </IconButton>
                        </ListItem>
                    )
                }
                </List>
                </Paper>
                </Paper>
            </Box>
            </>
    );
}

export default Applications;