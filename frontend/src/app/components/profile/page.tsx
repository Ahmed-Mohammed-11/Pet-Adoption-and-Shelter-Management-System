'use client';
import {Box} from "@mui/system";
import {Button, IconButton, Paper, TextField, Typography} from "@mui/material";
import {useState} from "react";
import EditIcon from "@mui/icons-material/Edit";
import SaveIcon from "@mui/icons-material/Save";
import SideBar from "@/app/components/side-bar/side-bar";
import clientValidateForm from "@/app/security/userValidation/clientFormValidation";

interface Props {
    itemList: any,
    userDetail: UserDTO,
    userType: string,
}

function Profile(props: Props) {

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

    //make this form data coming from props
    const [formData, setFormData] = useState({
        firstName: props.userDetail.firstName,
        lastName: props.userDetail.lastName,
        username: props.userDetail.username,
        email: props.userDetail.email,
        phone: props.userDetail.phone,
        password: props.userDetail.password,
        role: props.userDetail.role,
    });

    // Step 2: Implement Material-UI components for each field
    const [editMode, setEditMode] = useState({
        firstName: false,
        lastName: false,
        username: false,
        email: false,
        phone: false,
        password: false,
        role: false,
    });

    // Function to handle saving edited data
    const handleSave = (formData: UserDTO) => {
        setEditMode({
            firstName: false,
            lastName: false,
            username: false,
            email: false,
            phone: false,
            password: false,
            role: false,
        })

        // Step 3: Validate form data
        let {isUserValid, errors} = clientValidateForm(formData)
        setIsUserValid(isUserValid)
        setErrors(errors);
        console.log(isUserValid);
        console.log(errors);
    };

    console.log("ana gwa" + props.userDetail.firstName);

    return (

        // sidebar
        <Box display="flex" alignItems="stretch" justifyContent="center" height="100vh">
            <SideBar width={"20%"} userType={props.userType} itemList={props.itemList}/>

            {/* Right side: Flexbox containing text fields */}

            <Paper style={{padding: '20px', width: '80%'}}>
                <Typography variant="h3" gutterBottom color="primary">
                    Profile Contact Information
                </Typography>
                <TextField
                    label="First Name"
                    variant="outlined"
                    margin="normal"
                    fullWidth
                    value={props.userDetail.firstName}
                    onChange={(e) => setFormData({...formData, firstName: e.target.value})}
                    disabled={!editMode.firstName}
                    onClick={() => setEditMode({...editMode, firstName: true})}
                    InputProps={{
                        endAdornment: (
                            <IconButton color="primary" size="small">
                                {editMode.firstName ? null : <EditIcon/>}
                            </IconButton>
                        ),
                    }}
                />
                <TextField
                    label="Last Name"
                    variant="outlined"
                    margin="normal"
                    fullWidth
                    value={formData.lastName}
                    onChange={(e) => setFormData({...formData, lastName: e.target.value})}
                    disabled={!editMode.lastName}
                    onClick={() => setEditMode({...editMode, lastName: true})}
                    InputProps={{
                        endAdornment: (
                            <IconButton color="primary" size="small">
                                {editMode.lastName ? null : <EditIcon/>}
                            </IconButton>
                        ),
                    }}
                />
                <TextField
                    label="Username"
                    variant="outlined"
                    margin="normal"
                    fullWidth
                    value={formData.username}
                    error={!isUserValid.username}
                    helperText={isUserValid.username ? "" : errors.username}
                    onChange={(e) => setFormData({...formData, username: e.target.value})}
                    disabled={!editMode.username}
                    onClick={() => setEditMode({...editMode, username: true})}
                    InputProps={{
                        endAdornment: (
                            <IconButton color="primary" size="small">
                                {editMode.username ? null : <EditIcon/>}
                            </IconButton>
                        ),
                    }}
                />
                <TextField
                    label="Email"
                    type="email"
                    variant="outlined"
                    margin="normal"
                    fullWidth
                    value={formData.email}
                    error={!isUserValid.email}
                    helperText={isUserValid.email ? "" : errors.email}
                    onChange={(e) => setFormData({...formData, email: e.target.value})}
                    disabled={!editMode.email}
                    onClick={() => setEditMode({...editMode, email: true})}
                    InputProps={{
                        endAdornment: (
                            <IconButton color="primary" size="small">
                                {editMode.email ? null : <EditIcon/>}
                            </IconButton>
                        ),
                    }}
                />
                <TextField
                    label="Phone"
                    variant="outlined"
                    margin="normal"
                    fullWidth
                    value={formData.phone}
                    onChange={(e) => setFormData({...formData, phone: e.target.value})}
                    disabled={!editMode.phone}
                    onClick={() => setEditMode({...editMode, phone: true})}
                    InputProps={{
                        endAdornment: (
                            <IconButton color="primary" size="small">
                                {editMode.phone ? null : <EditIcon/>}
                            </IconButton>
                        ),
                    }}
                />
                <TextField
                    label="Password"
                    type="password"
                    variant="outlined"
                    margin="normal"
                    fullWidth
                    value={formData.password}
                    error={!isUserValid.password}
                    helperText={isUserValid.password ? "" : errors.password}
                    onChange={(e) => setFormData({...formData, password: e.target.value})}
                    disabled={!editMode.password}
                    onClick={() => setEditMode({...editMode, password: true})}
                    InputProps={{
                        endAdornment: (
                            <IconButton color="primary" size="small">
                                {editMode.password ? null : <EditIcon/>}
                            </IconButton>
                        ),
                    }}
                />
                <TextField
                    label="Role"
                    variant="outlined"
                    margin="normal"
                    fullWidth
                    disabled={true}
                    value={formData.role}
                />

                <Button
                    type="submit"
                    variant="contained"
                    color="primary"
                    onClick={() => handleSave(formData)}
                    startIcon={<SaveIcon/>}
                >
                    Save
                </Button>
            </Paper>
        </Box>
    );
}

export default Profile;