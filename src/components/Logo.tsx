import logo1 from "../assets/logo3.png";

const Logo = () => {
  return (
    <a className="navbar-brand" href="#">
      <img
        src={logo1}
        alt="logo"
        width={48}
        height={48}
        className="rounded-circle"
      />
    </a>
  );
};

export default Logo;
