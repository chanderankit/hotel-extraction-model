package com.extraction.service;

import com.extraction.model.EntityDto;
import com.extraction.util.HotelMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class HotelServiceImpl implements HotelService {
    @Override
    public int exportData(EntityDto fileDto) {
        List<String[]> hotelData = HotelMapper.getHotelData(fileDto);
        try {
            convertDataArrayToCSV("hotelData.csv", hotelData);
            log.info("-----{}-----",hotelData.size());
            return hotelData.size();
        } catch (IOException ex) {
            log.error("Error on creating urlRedirection csv file: === {}", ex.getMessage());
        }
        log.info("-----{}-----",hotelData.size());
        return hotelData.size();
    }


    private static byte[] convertDataArrayToCSV(String fileName, List<String[]> dataLines) throws IOException {
        File csvOutputFile = new File(fileName);
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            dataLines.stream().map(dataLine -> convertToCSV(dataLine)).forEach(pw::println);
        }
        return getByteArrayFromFile(fileName);
    }


    private static byte[] getByteArrayFromFile(String filename) throws IOException {
        File file = new File(filename);
        byte[] bytesArray = new byte[(int) file.length()];
        try (FileInputStream fis = new FileInputStream(file)) {
            fis.read(bytesArray);
            fis.close();
        } catch (FileNotFoundException e) {

            log.info(e.getMessage());
        }
        return bytesArray;
    }


    private static String convertToCSV(String[] data) {
        return Stream.of(data).collect(Collectors.joining(","));
    }

}
