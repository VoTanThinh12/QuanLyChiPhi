import * as Yup from "yup";
const profileValidationSchema = Yup.object().shape({
  name: Yup.string()
    .required("Name is required")
    .min(3, "Name must be at least 3 characters long"),
  email: Yup.string()
    .required("Email is required")
    .email("Email is not invalid"),
  password: Yup.string()
    .required("Password is required")
    .min(5, "Password must be at least 5 characters"),

  confirmPassword: Yup.string()
    .required("Retype comfirm password")
    .oneOf([Yup.ref("password")], "Password must match"),
  //   confirmPassword: Yup.string()
  // .required("Retype confirm password")
  // .oneOf([Yup.ref("password")], "Passwords must match"),
});
export default profileValidationSchema;
