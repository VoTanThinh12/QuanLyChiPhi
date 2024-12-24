import { useState } from "react";
import { Profile } from "../model/Profile";
import { createProfile } from "../services/auth-service";

export const useRegister = () => {
  const [error, setError] = useState<string>("");
  const [isLoading, setLoader] = useState<boolean>(false);
  const [toast, setToast] = useState<string>("");
  const signup = (profile: Profile) => {
    console.log("values", profile);
    setLoader(true);
    createProfile(profile)
      .then((response) => {
        if (response.status === 201) {
          //   resetForm();
          setToast("Registration successful");
        }
      })
      .catch((error) => console.log(error))
      .finally(() => setLoader(false));
  };
  return { error, isLoading, toast, signup };
};
