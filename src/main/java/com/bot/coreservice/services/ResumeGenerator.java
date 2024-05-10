package com.bot.coreservice.services;

import com.bot.coreservice.contracts.IResumeGenerator;
import com.bot.coreservice.model.RequestModel;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
@Service
public class ResumeGenerator implements IResumeGenerator {

    public String ResumeGeneratorService(RequestModel requestModel) throws IOException {
        if (requestModel.getContent().isEmpty())
            throw new IOException("Content is null");

        String tokenResult = null;
        try {
            URL url = new URL("https://www.emstum.com/bot/dn/gen/api/FileGenerator/generate_pdf_bytes");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "*/*");
            try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
                wr.writeBytes(requestModel.getContent());
                wr.flush();
            }

            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);
            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                System.out.println("Response: " + response.toString());
                tokenResult = response.toString().replaceAll("\"", "");
            }
            connection.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return tokenResult;
    }
}
