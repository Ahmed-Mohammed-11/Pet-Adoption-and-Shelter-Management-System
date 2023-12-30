'use client';
import React from 'react';
import { useState } from 'react';
import styles from './page.module.css';
import {Box, Button, Checkbox, FormControlLabel, IconButton, TextField, Typography} from '@mui/material';
import EditIcon from "@mui/icons-material/Edit";
import getRequestController from "@/app/services/getRequestController";

function PetFilter() {
    // Step 2: Set up state
    const [filterCriteria, setFilterCriteria] = useState({ age: '', breed: '', species: '', houseTrained: HTMLElement });
    const [filteredAnimals, setFilteredAnimals] = useState([]);

    const buildUrl = () => {
        let url = "/pets?1=1";
        if (filterCriteria.age !== '') url += "&age=" + filterCriteria.age;
        if (filterCriteria.breed !== '') url += "&breed=" + filterCriteria.breed;
        if (filterCriteria.species !== '') url += "&species=" + filterCriteria.species;
        if (filterCriteria.houseTrained !== null) url += "&houseTrained=" + filterCriteria.houseTrained;
        return url;
    }

    const filterPets = async () => {
        let url = buildUrl();
        console.log(url);
        let response = await getRequestController.sendGetRequest(url);
        let jsonResponse = await response.json();
        console.log(jsonResponse);
    }

    return (
        <Box className={styles.container}>
                <TextField
                    className={styles.input}
                    label="Age"
                    size={"small"}
                    type="number"
                    value={filterCriteria.age}
                    onChange={(e) => setFilterCriteria({ ...filterCriteria, age: e.target.value })}
                    InputProps={{endAdornment: "Years"}}
                />

                <span></span>
                <TextField
                    className={styles.input}
                    label="Breed"
                    size={"small"}
                    type="text"
                    value={filterCriteria.breed}
                    onChange={(e) => setFilterCriteria({ ...filterCriteria, breed: e.target.value })}
                />
                <span></span>
                <TextField
                    className={styles.input}
                    size={"small"}
                    label="Species"
                    type="text"
                    value={filterCriteria.species}
                    onChange={(e) => setFilterCriteria({ ...filterCriteria, species: e.target.value })}
                />
                <span></span>
                <FormControlLabel
                    control={<Checkbox />}
                    label="House Training"
                    value={filterCriteria.houseTrained}
                    //if checked, set value to true, else set to false
                    onChange={(e) => setFilterCriteria({ ...filterCriteria, houseTrained: e.target.checked })}

                />
                <Button onClick={filterPets}>Filter</Button>
        </Box>
    );
};

export default PetFilter;
