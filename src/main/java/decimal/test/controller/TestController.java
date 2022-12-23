package decimal.test.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import decimal.test.dto.ResponseDTO;
import decimal.test.dto.TestDTO;
import decimal.test.execption.GenralException;
import decimal.test.service.TestService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

import java.util.logging.Logger;



@RestController
public class TestController {


    private static final Logger logger = Logger.getLogger(TestController.class.getName());
    @Autowired
    private TestService testService;


    @PostMapping(value = "save")
    public String saveTestData(@RequestBody TestDTO testDTO) {
        testService.saveTestData (testDTO);
        return "SUCCESS";
    }


//    @PostMapping(value = "getById")
//    public TestDTO getById(@RequestBody TestDTO testDTO)
//    {
//        return testService.getById(testDTO.getId());
//    }

    // getById using Exception:
    @PostMapping(value = "getById")
    public ResponseEntity getById(@RequestBody TestDTO testDTO) throws GenralException {
        ResponseDTO responseDTO = testService.getById (testDTO.getId ());
        return new ResponseEntity (responseDTO, HttpStatus.OK);
    }


//    @PostMapping(value = "getByName")
//    public List<TestDTO> getByName(@RequestBody TestDTO testDTO)
//    {
//        return testService.getByName(testDTO.getName());
//    }

    // getByName using Exception:
    @PostMapping(value = "getByName")
    public ResponseEntity getByName(@RequestBody TestDTO testDTO) throws GenralException {
        ResponseDTO responseDTO = testService.getByName (testDTO.getName ());
        return new ResponseEntity (responseDTO, HttpStatus.OK);
    }

//    @PostMapping(value = "getByAddress")
//    public List<TestDTO> getByAddress(@RequestBody TestDTO testDTO)
//    {
//        return testService.getByAddress(testDTO.getAddress());
//    }

    // getByAddress using Exception:
    @PostMapping(value = "getByAddress")
    public ResponseEntity getByAddress(@RequestBody TestDTO testDTO) {
        ResponseDTO responseDTO = testService.getByAddress (testDTO.getAddress ());
        return new ResponseEntity (responseDTO, HttpStatus.OK);
    }

    @PostMapping(value = "getByIdAndName")
    public TestDTO getByIdAndName(@RequestBody TestDTO testDTO) {
        return testService.getByIdAndName (testDTO.getId (), testDTO.getName ());
    }


    @PostMapping(value = "getByNameAndAddress")
    public List<TestDTO> getByNameAndAddress(@RequestBody TestDTO testDTO) {
        return testService.getByNameAndAddress (testDTO);
    }

    @PostMapping("getByIdAndAddress")
    public TestDTO getByIdAndAddress(@RequestBody TestDTO testDTO) {
        return testService.getByIdAndAddress (testDTO);
    }

    @PostMapping(value = "deleteById")
    public String deletedById(@RequestBody TestDTO testDTO) {
        testService.deleteById (testDTO.getId ());
        return "SUCCESS";
    }

    @PostMapping(value = "deleteByName")
    public String deletedByName(@RequestBody TestDTO testDTO) {
        testService.deleteByName (testDTO.getName ());
        return "SUCCESS";
    }

    @PostMapping(value = "deleteByAddress")
    public String deleteByAddress(@RequestBody TestDTO testDTO) {
        testService.deleteByAddress (testDTO);
        return "SUCCESS";
    }

    @PostMapping("deleteByIdAndName")
    public String deleteByIdAndName(@RequestBody TestDTO testDTO) {
        testService.deleteByIdAndName (testDTO);
        return "SUCCESS";
    }

    @PostMapping("deleteByIdAndAddress")
    public String deleteByIdAndAddress(@RequestBody TestDTO testDTO) {
        testService.deleteByIdAndAddress (testDTO);
        return "SUCCESS";
    }


    @PostMapping("deleteByNameAndAddress")
    public String deleteByNameAndAddress(@RequestBody TestDTO testDTO) {
        testService.deleteByNameAndAddress (testDTO);
        return "SUCCESS";
    }


    @PostMapping("updateByName")
    public String updateDBName(@RequestBody TestDTO testDTO) {
        testService.updateByName (testDTO);
        return "Database updated";
    }

    @PostMapping("updateById")
    public String updateDBId(@RequestBody TestDTO testDTO) {
        testService.updateById (testDTO);
        return "Database is update with Id";
    }


    @PostMapping("updateByAddress")
    public String updateDbAddress(@RequestBody TestDTO testDTO) {
        testService.updateByAddress (testDTO);
        return "Database is update with Address";
    }

    @PostMapping("updateByIdAndName")
    public String updateDbIdAndName(@RequestBody TestDTO testDTO) {
        testService.updateByIdAndName (testDTO);
        return "Database is update with Id and Name";
    }


    @PostMapping("updateByIdAndAddress")
    public String updateDbIdAndAddress(@RequestBody TestDTO testDTO) {
        testService.updateByIdAndAddress (testDTO);
        return "Database is update with Id and Address";
    }


    @PostMapping("updateByNameAndAddress")
    public String updateDbNameAndAddress(@RequestBody TestDTO testDTO) {
        testService.updateByNameAndAddress (testDTO);
        return "Database is update with Name and Address";
    }

    @PostMapping("/dummy-aml")
    public Object dummyAmlRequest(@RequestBody Object request) throws JsonProcessingException {
        logger.info("Inside Dummy AML Controller Service");

        String s = ""+request;
        logger.info("Request: "+s);

        JSONObject obj = new JSONObject();
        obj.put ("AML_Score", "No Match Found");
        logger.info("JSON Conversion: " + obj);
        return new ObjectMapper().readTree (obj.toString ());
    }

    @PostMapping("/dummy-checkEOD")
    public Object dummyEodAPI(@RequestBody Object request) throws JsonProcessingException {
        logger.info("Inside dummy EodAPI");

        JSONObject obj = new JSONObject();
        obj.put("EODFlag", "1");
        logger.info("EODFlag: " + obj);
        return new ObjectMapper().readTree (obj.toString ());
    }

    @PostMapping("/dummy-dedupeAadhaar")
    public Object dummyDedupeAadhaarServiceControl(@RequestBody Object request) throws JsonProcessingException {
        logger.info("Inside dummy DedupeAadhaarServiceControl Method");
        JSONObject obj = new JSONObject();
        obj.put("Dedupe_Aadhaar_Response", "Record not found");
        return new ObjectMapper().readTree (obj.toString ());
    }

    @PostMapping("/dummy-dedupeEmail")
    public ResponseEntity<Object> dummyDedupeControl(@RequestBody String request){
        logger.info("Inside dummy DedupeEmailServiceControl Method");

        JSONObject result = new JSONObject();
        result.put("Flag","N");
        logger.info(String.valueOf(result));
        return new ResponseEntity<>(result.toString(), HttpStatus.OK);
    }

    @PostMapping("/dummy-dedupePan")
    public Object dummyDedupePanServiceControl(@RequestBody Object request) throws JsonProcessingException {
        logger.info ("Inside dummy DedupePanServiceControl Method");
        JSONObject obj = new JSONObject();
        obj.put("Dedupe_PAN_Response", "Record not found");
        return new ObjectMapper().readTree (obj.toString ());
    }

    @PostMapping("/dummy-pan")
    public Object dummyPanApi(@RequestBody Object request) throws JsonProcessingException {

        logger.info ("Inside dummy PanAPI Method");

        String strReq = "" + request;
        logger.info("strObj: " + strReq);

        String panNumber = parsingLastParam("panNumber=", strReq);

        JSONObject obj = new JSONObject();
        obj.put("PanNo", panNumber);
        obj.put("PAN_STATUS", "Existing and Valid PAN");
        obj.put("LastName", "LastName");
        obj.put("FirstName", "FirstName");
        obj.put("MiddleName", "MiddleName");
        obj.put("Pan_Title", "Shri");
        obj.put("LastUpdateDate", "DD/MM/YYYY");
        obj.put("NameOnCard", "XXXX XXXX");
        obj.put("AadhaarSeedingStatus", "Y");

        logger.info("objRes: " + obj);

        return new ObjectMapper().readTree (obj.toString ());
    }
    public static String parsingLastParam(String panStr, String objReq) {

        int firstIndex = objReq.indexOf(panStr);
        int lastIndex = objReq.indexOf("}", firstIndex);
        String returnStr = objReq.substring(firstIndex + panStr.length(), lastIndex);

        return returnStr;
    }
    @Value("${demo.value.CA_ID}")
    private String CA_ID;

    @PostMapping("/dummy-demoAuth")
    public String dummyDemoRequest(@RequestBody Map<String, Object> request) {
        logger.info("json: " + request);

        String adhaarNo = request.get ("aadhaarNumber").toString ();
        String stan = String.valueOf(generateRandomDigits(6));

        String ret = new String();
        JSONObject jsonobj = new JSONObject();
        jsonobj.put("responseMessage","Approved");
        jsonobj.put("txn", stan);
        jsonobj.put("adhaarNo", adhaarNo);
        jsonobj.put("CA_ID", CA_ID);
        jsonobj.put("responseCode","00");
        ret = jsonobj.toString();
        return ret;
    }

    private static int generateRandomDigits(int n) {
        int m = (int) Math.pow(10, n - 1);
        return m + new Random().nextInt(9 * m);
    }

    @Value("${otp.value.CA_ID}")
    private String OCA_ID;
    @PostMapping("/dummy-otp")
    public ResponseEntity<Object> dummyOtpRequest(@RequestBody Map<String, Object> request) {
        logger.info("Aadhaar OTP Request: " + request);

        String adhaarNo = request.get ("aadhaarNumber").toString ();
        String stan = String.valueOf(generateRandomDigits(6));

        JSONObject obj = new JSONObject();
        obj.put("responseMessage", "Approved");
        obj.put("txn", stan);
        obj.put("adhaarNo", adhaarNo);
        obj.put("CA_ID", OCA_ID);
        logger.info("Response = " + obj);
        return new ResponseEntity<>(obj.toMap(), HttpStatus.OK);
    }

    @PostMapping("/dummy-ekyc")
    public ResponseEntity<Object> dummyOtpRequest1(@RequestBody Map<String, Object> request) throws Exception {

        logger.info("Aadhaar EKYC Request: " + request);

        JSONObject obj = new JSONObject();
        obj.put("responseMessage", "Approved");
        obj.put("pdfData", "");
        obj.put("phtData", "");
        obj.put("name", "Mritunjay Kumar Pathak");
        obj.put("gender", "M");
        obj.put("dob", "06-10-1991");
        obj.put("vtc", "Gutuwa");
        obj.put("street", "itki road");
        obj.put("state", "Jharkhand");
        obj.put("pc", "835303");
        obj.put("loc", "kathal more");
        obj.put("lm", "near mecon colony");
        obj.put("house", "134 E");
        obj.put("dist", "Ranchi");
        obj.put("country", "India");
        obj.put("DO", "");
        obj.put("SO", "Satyanarayan Pathak");
        return new ResponseEntity<>(obj.toMap(), HttpStatus.OK);
    }
}

