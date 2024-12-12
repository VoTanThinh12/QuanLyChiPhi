import { Link } from "react-router-dom";
import logo1 from "../assets/logo3.png";

const Logo = () => {
  return (
    <Link className="navbar-brand" to="/">
      <img
        src={logo1}
        alt="logo"
        width={48}
        height={48}
        className="rounded-circle"
      />
    </Link>
  );
};

export default Logo;
