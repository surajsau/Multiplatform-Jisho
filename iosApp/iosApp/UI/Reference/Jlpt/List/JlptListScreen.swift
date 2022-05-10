//
//  JlptListScreen.swift
//  iosApp
//
//  Created by Suraj GP on 10/05/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct JlptListScreen: View {
    
    @ObservedObject private var dispatcher = Dispatcher(viewModel: JlptListViewModel.init())
    
    let level: Int
    
    var body: some View {
        ZStack {
            
            Color.Neomorph.main
                .edgesIgnoringSafeArea(.all)
            
            VStack {

                AppTopBar(navigateUpIcon: "arrow.backward", title: self.dispatcher.state?.title ?? "")
                    .padding()
                
                if let state = self.dispatcher.state {
                    
                    
                }
            }
        }
        .onAppear {
            self.dispatcher.with(intent: JlptListViewModelIntentInitWith(level: Int32(self.level)))
        }
        .navigationBarHidden(true)
        .navigationBarBackButtonHidden(true)
    }
}
