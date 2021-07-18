package com.example.aditya;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {


    Test test;
    ArrayList<DownModel> downModels;

    public MyAdapter(Test test, ArrayList<DownModel> downModels) {
        this.test = test;
        this.downModels = downModels;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(test.getBaseContext());
        View view = layoutInflater.inflate(R.layout.elements, null, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {
        myViewHolder.mName.setText(downModels.get(i).getName());
        myViewHolder.mLink.setText(downModels.get(i).getLink());
        myViewHolder.mDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadFile(myViewHolder.mName.getContext(),downModels.get(i).getName(),"."+AfterLogin.ext,DIRECTORY_DOWNLOADS,downModels.get(i).getLink());
            }
        });
    }

    public void downloadFile(Context context, String fileName, String fileExtension, String destinationDirectory, String url) {

        DownloadManager downloadmanager = (DownloadManager) context.
                getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context, destinationDirectory, fileName + fileExtension);

        downloadmanager.enqueue(request);
    }







    @Override
    public int getItemCount() {
        return downModels.size();
    }
}
