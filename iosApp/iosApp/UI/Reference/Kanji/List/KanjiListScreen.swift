//
//  KanjiListScreen.swift
//  iosApp
//
//  Created by Suraj GP on 09/05/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct KanjiListScreen: View {
    
    @Environment(\.presentationMode) var presentationMode
    
    @ObservedObject private var dispatcher = Dispatcher(viewModel: KanjiListViewModel.init())
    
    let query: KanjiQuery
    
    var body: some View {
        ZStack {
            Color.Neomorph.main
                .edgesIgnoringSafeArea(.all)
            
            if let state = self.dispatcher.state {
                VStack {
                    AppTopBar(navigateUpIcon: "arrow.backward", title: state.title) {
                        self.presentationMode.wrappedValue.dismiss()
                    }
                        .padding()
                    
                    ScrollView {
                        LazyVGrid(columns: [GridItem(.adaptive(minimum: 72), spacing: 24)], spacing: 16) {
                            ForEach(state.items, id: \.self) { item in
                                KanjiGradeItem(grade: item.value)
                            }
                        }
                        .padding()
                    }
                    
                    Spacer()
                }
            }
        }
        .onAppear {
            dispatcher.with(intent: KanjiListViewModelIntentInitWith(query: self.query))
        }
        .navigationBarBackButtonHidden(true)
        .navigationBarHidden(true)
    }
}
