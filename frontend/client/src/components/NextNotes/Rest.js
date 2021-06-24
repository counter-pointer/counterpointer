import { Icon } from "@material-ui/core";
import PropTypes from "prop-types";
import React from "react";
import HalfRest from "../../assets/halfRest.svg";
import QuarterRest from "../../assets/quarterRest.svg";
import WholeRest from "../../assets/wholeRest.svg";


const durationToSymbol = (duration) => {
  switch (duration) {
    case 1:
      return QuarterRest;
    case 2:
      return HalfRest;
    case 4:
      return WholeRest;
    default:
      return QuarterRest;
  }
}

const Rest = ({duration}) => {
  return <Icon fontSize="inherit">
    <img src={durationToSymbol(duration)} alt="duration" width={14} height={14}/>
  </Icon>;
}

Rest.propTypes = {
  duration: PropTypes.number,
};

export default Rest;