import logo from './logo.svg';
import './App.css';
import React, {useState,useEffect,useCallback} from "react";
import axios from "axios";
import { useDropzone } from 'react-dropzone';

const UserProfiles = () => {
  //get var
  const [userProfiles, setUserProfiles] = useState([]);

  const fetchUserProfiles = () => {
    axios.get("http://localhost:8080/api/v1/user-profile").then((res) => {
      console.log(res);
      setUserProfiles(res.data);
    })
        .catch(error => console.log(error));
  };

  useEffect(() => {
    fetchUserProfiles();
  }, []);
 
  return userProfiles.map((userProfile,index) =>{
    return(
      <div key={index}>
        {/* {todo: profile image} */}
        <br />
        <br />
        <h1>{userProfile.username}</h1>
        <p>{userProfile.userProfileId}</p>
        
        <MyDropzone />
      </div>
    )
  })
};

function MyDropzone() {
  const onDrop = useCallback(acceptedFiles => {
    // Do something with the files
    const file = acceptedFiles[0];
    console.log(file);
  }, [])
  const {getRootProps, getInputProps, isDragActive} = useDropzone({onDrop})

  return (
    <div {...getRootProps()}>
      <input {...getInputProps()} />
      {
        isDragActive ?
          <p>Drop the files here ...</p> :
          <p>Drag 'n' drop some image here</p>
      }
    </div>
  )
}

function App() {
  return (
    <div className="App">
      <UserProfiles />
    </div>
  );
}

export default App;
